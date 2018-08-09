package com.example.tommacbookpro.myproject.fragment

import android.os.Bundle
import android.support.design.widget.BottomSheetBehavior
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import butterknife.BindView
import com.example.tommacbookpro.myproject.adapter.ContentAdapter
import com.example.tommacbookpro.myproject.manager.singleton.HttpManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.tommacbookpro.myproject.R
import com.example.tommacbookpro.myproject.dao.NewPhotoItemDao
import com.example.tommacbookpro.myproject.dao.NewStatusPost
import com.example.tommacbookpro.myproject.dao.PhotoItemCollectionDao
import com.example.tommacbookpro.myproject.dao.Post
import com.example.tommacbookpro.myproject.utility.ClickListener
import com.example.tommacbookpro.myproject.utility.Contextor
import kotlinx.android.synthetic.main.fragment_main.*


class FragmentMain : Fragment(), ClickListener {
    override fun itemClicked(view: View?, position: Int) {
        Toast.makeText(Contextor.getInstance().context, "Position"+position, Toast.LENGTH_SHORT).show()
        Log.e("dataclick","dataclick"+position)
    }

    private lateinit var adapter: ContentAdapter
    /*   val users : PhotoItemCollectionDao? = null
       val users: MutableList<PhotoItemCollectionDao>? = null
    var users : PhotoItemCollectionDao? = null*/

    val post : MutableList<Post> = ArrayList()
    @BindView(R.id.bottom_sheet)
    internal var layoutBottomSheet: LinearLayout? = null

    private lateinit var sheetBehavior: BottomSheetBehavior<*>
    internal lateinit var swipeRefreshLayout: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        if (savedInstanceState != null);

    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_main, container, false)
        swipeRefreshLayout = rootView.findViewById<View>(R.id.swipLayoutRefresh) as SwipeRefreshLayout
        /* btnRefresh.setOnClickListener {
             toggleBottomSheet()
         }*/
        loadData()

        refresh()
        return rootView
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)


    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (savedInstanceState != null){
            Log.e("FragmentDie","FragmentDie"+savedInstanceState)
        }
    }
    /*
        private fun initBottomSheet() {
           */
/* sheetBehavior = BottomSheetBehavior.from(bottom_sheet)*//*

        sheetBehavior = BottomSheetBehavior.from(layoutBottomSheet)
        sheetBehavior.setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when (newState) {
                    BottomSheetBehavior.STATE_HIDDEN -> {
                    }
                    BottomSheetBehavior.STATE_EXPANDED -> {
                        btnRefresh!!.text = "Close Sheet"
                    }
                    BottomSheetBehavior.STATE_COLLAPSED -> {
                        btnRefresh!!.text = "Expand Sheet"
                    }
                    BottomSheetBehavior.STATE_DRAGGING -> {
                    }
                    BottomSheetBehavior.STATE_SETTLING -> {
                    }
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {

            }
        })
    }


    fun toggleBottomSheet() {
        if (sheetBehavior.state != BottomSheetBehavior.STATE_EXPANDED) {
            sheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
            btnRefresh!!.text = "Close sheet"
        } else {
            sheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
            btnRefresh!!.text = "Expand sheet"
        }
    }*/
    fun loadData(){
        val call = HttpManager.ApiService()
        call.loadPhotoList().enqueue(object: Callback<PhotoItemCollectionDao?> {
            override fun onResponse(call: Call<PhotoItemCollectionDao?>?, response: Response<PhotoItemCollectionDao?>?) {
                val dao = response?.body()

                setupRecyclerview(dao)
                genPhoto(dao)

            }

            override fun onFailure(call: Call<PhotoItemCollectionDao?>?, t: Throwable?) {

            }


        })
    }

    private fun genPhoto(dao: PhotoItemCollectionDao?) {

        for (item in dao!!.data){
            val post1 = NewPhotoItemDao(item.id.toString(),item.caption,item.createdTime,item.imageUrl)
            val post2 = NewStatusPost(item.id.toString(),item.caption,item.createdTime)
            post.add(post1)
            post.add(post2)
        }
        Log.e("testtest","testtest"+ post)

    }

    private fun setupRecyclerview(dao: PhotoItemCollectionDao?) {

        var dataList = dao?.data
        /*  Log.e("dataList","dataList"+ GsonBuilder().setPrettyPrinting().create().toJson(dataList))*/

        Log.e("dataList", "dataList$dataList")
        /*   adapter = InfoAdapter(dao)*/
        adapter = ContentAdapter()
        adapter.submitList(post)
        /*recyclerView.layoutManager = LinearLayoutManager(Contextor.getInstance().context,LinearLayoutManager.HORIZONTAL,false)*/
        recyclerView.layoutManager = LinearLayoutManager(Contextor.getInstance().context)
        recyclerView.adapter = adapter
        adapter.setClickListener(this)
    }

    private fun refresh() {
        swipeRefreshLayout.setOnRefreshListener { swipeRefreshLayout.isRefreshing = false }
    }
    override fun onStart() {
        super.onStart()
    }

    override fun onStop() {
        super.onStop()
    }




    companion object {

        fun newInstance(): FragmentMain {
            val fragment = FragmentMain()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }
}
