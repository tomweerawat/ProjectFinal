package com.example.tommacbookpro.myproject.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.tommacbookpro.myproject.adapter.MenuHomeAdapter
import com.example.tommacbookpro.myproject.MainActivity
import com.example.tommacbookpro.myproject.dao.dummydata.MenuGenerator
import com.example.tommacbookpro.myproject.R
import com.example.tommacbookpro.myproject.constant.Conts
import com.example.tommacbookpro.myproject.dao.Post
import com.example.tommacbookpro.myproject.utility.ClickListener
import com.example.tommacbookpro.myproject.utility.Contextor
import com.google.android.gms.common.api.GoogleApiClient
import kotlinx.android.synthetic.main.view_menu_profile.*



class HomeMenuActivity : AppCompatActivity(), ClickListener, GoogleApiClient.ConnectionCallbacks {
    override fun onConnectionSuspended(p0: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onConnected(p0: Bundle?) {
        if(LoginFirebase.mGoogleApiClient!!.isConnected){
            Toast.makeText(this, "Connected", Toast.LENGTH_SHORT).show()
        }
    }

    val post : MutableList<Post> = ArrayList()
    override fun itemClicked(view: View?, position: Int) {
        if (position == 0) {
            startActivity(Intent(this@HomeMenuActivity, MainActivity::class.java))

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_menu_profile)
        initview()
        var pref = getSharedPreferences("userdata", Context.MODE_PRIVATE)
        Log.e("MyImage", "MyImage" + pref.getString(Conts.USERIMAGE, ""))
        etxt.text = pref.getString(Conts.EMAIL, "")
        profile_image.setOnClickListener {
            LoginFirebase.signOut()
            val preferences = getSharedPreferences("userdata", Context.MODE_PRIVATE)
            val editor = preferences.edit()
            editor.clear()
            editor.apply()
            startActivity(Intent(this@HomeMenuActivity, LoginFirebase::class.java))

        }
    }

    private fun initview() {
        val layoutManager = GridLayoutManager(Contextor.getInstance().context, 2)
        rvAndroidVersion.layoutManager = layoutManager
        rvAndroidVersion.setHasFixedSize(true)
        val adapter = MenuHomeAdapter(MenuGenerator.createAndroidVersionInfo())
        rvAndroidVersion.adapter = adapter
        adapter.setClickListener(this)

    }




}