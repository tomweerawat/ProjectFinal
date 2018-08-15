package com.example.tommacbookpro.myproject.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.tommacbookpro.myproject.manager.singleton.HttpManager

import com.example.tommacbookpro.myproject.adapter.DessertAdapter
import com.example.tommacbookpro.myproject.dao.CollectionX
import com.example.tommacbookpro.myproject.dao.DessertItemDao
import com.example.tommacbookpro.myproject.R
import com.example.tommacbookpro.myproject.utility.ClickListener
import com.example.tommacbookpro.myproject.utility.Contextor

import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.fragment_cart.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.R.attr.data
import com.example.tommacbookpro.myproject.activity.DetailActivity
import android.content.Intent




class CartFragment : Fragment(), ClickListener {
    override fun itemClicked(view: View?, position: Int) {
        Toast.makeText(Contextor.getInstance().context, "Hello$position", Toast.LENGTH_SHORT).show()
        val i = Intent(activity, DetailActivity::class.java)
        i.putExtra("position",position)
        i.putParcelableArrayListExtra("detail", ArrayList(collectionX))
        startActivity(i)
    }

    val collectionX : MutableList<CollectionX> = ArrayList()
    companion object {

        fun newInstance(param1: String, param2: String): CartFragment {
            val fragment = CartFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }
    val TAG:String = "CartFragment"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_cart, container, false)

        loadData()


        return rootView
    }

    private fun loadData() {
        val call = HttpManager.DessertService()
        call.loadDessertList(1).enqueue(object: Callback<DessertItemDao?> {
            override fun onResponse(call: Call<DessertItemDao?>?, response: Response<DessertItemDao?>?) {
                val dao = response!!.body()
                val newdao = dao!!.collections
                Log.e(TAG, "Dessert" + GsonBuilder().setPrettyPrinting().create().toJson(newdao))

                for (item in newdao){
                   /* val post1 = NewCollectionX(item.collection.collectionId,item.collection.resCount,item.collection.imageUrl,item.collection.url,item.collection.title,item.collection.description,item.collection.shareUrl)*/
                    val post1 = CollectionX(item.collection.collectionId,item.collection.resCount,item.collection.imageUrl,item.collection.url,item.collection.title,item.collection.description,item.collection.shareUrl)
                    collectionX.add(post1)
                }
                val layoutManager = LinearLayoutManager(Contextor.getInstance().context)
                recyclerView2.layoutManager = layoutManager
                recyclerView2.setHasFixedSize(true)
                val adapter = DessertAdapter(collectionX)
                recyclerView2.adapter = adapter
                adapter.setClickListener(this@CartFragment)
            }

            override fun onFailure(call: Call<DessertItemDao?>?, t: Throwable?) {

            }


        })
    }


}// Required empty public constructor
