package com.example.tommacbookpro.myproject

import android.content.res.Configuration
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.design.widget.BottomSheetBehavior
import android.support.design.widget.CoordinatorLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout
import com.example.tommacbookpro.myproject.fragment.FragmentMain
import com.example.tommacbookpro.myproject.adapter.ContentAdapter
import com.example.tommacbookpro.myproject.adapter.pageradapter.ViewPagerAdapter
import com.example.tommacbookpro.myproject.dao.MovieItemDao
import com.example.tommacbookpro.myproject.dao.Post
import com.example.tommacbookpro.myproject.fragment.CartFragment
import com.example.tommacbookpro.myproject.fragment.GiftsFragment
import com.example.tommacbookpro.myproject.utility.helper.BottomNavigationBehavior
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private lateinit var adapter: ContentAdapter
    internal lateinit var drawerLayout: DrawerLayout
    private lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    internal lateinit var toolbar: Toolbar
    private var viewPager: ViewPager? = null
    val post : MutableList<Post> = ArrayList()

    private lateinit var sheetBehavior: BottomSheetBehavior<*>
    private var layoutBottomSheet: LinearLayout? = null
    internal lateinit var dao: MovieItemDao
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initInstances()
      /*  dao = intent.getParcelableExtra("dao")
        Log.e("KotlinFragment", "KotlinFragment" + GsonBuilder().setPrettyPrinting().create().toJson(dao))*/
    }

    private fun initInstances() {
        /*  viewPager = findViewById<View>(R.id.viewPager) as ViewPager
        setupViewPager(viewPager)*/
        toolbar = findViewById(R.id.toolBarr)
        setSupportActionBar(toolbar)
        drawerLayout = findViewById(R.id.drawerLayout)

        actionBarDrawerToggle = ActionBarDrawerToggle(
                this@MainActivity,
                drawerLayout,
                R.string.open_drawer,
                R.string.close_drawer
        )
        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        supportActionBar!!.setHomeButtonEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)


        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        // attaching bottom sheet behaviour - hide / show on scroll
        val layoutParams = navigation.layoutParams as CoordinatorLayout.LayoutParams
        layoutParams.behavior = BottomNavigationBehavior()
        loadFragment(CartFragment())


    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        actionBarDrawerToggle.syncState()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        actionBarDrawerToggle.onConfigurationChanged(newConfig)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (actionBarDrawerToggle.onOptionsItemSelected(item)) true else super.onOptionsItemSelected(item)
    }


    fun setupViewPager(upViewPager: ViewPager?) {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(FragmentMain(), "")
        viewPager!!.adapter = adapter
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        val fragment: Fragment
        when (item.itemId) {
            R.id.navigation_shop -> {
                toolbar!!.title = "Recipe"
                fragment = CartFragment()
                loadFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_gifts -> {
                toolbar!!.title = "My Gifts"
                fragment = GiftsFragment()
                loadFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_cart -> {
                toolbar!!.title = "Cart"
                fragment = FragmentMain()
                loadFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_profile -> {
                toolbar!!.title = "Profile"
                fragment = MapsFragment()
                loadFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
        }

        false
    }

    private fun loadFragment(fragment: Fragment) {
        // load fragment
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frame_container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.navigation,menu)
        return true
    }
}
