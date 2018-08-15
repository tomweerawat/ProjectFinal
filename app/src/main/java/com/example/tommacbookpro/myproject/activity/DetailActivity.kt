package com.example.tommacbookpro.myproject.activity


import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.tommacbookpro.myproject.R
import com.example.tommacbookpro.myproject.dao.CollectionX
import com.example.tommacbookpro.myproject.utility.Contextor
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.detail_activity.*
import kotlinx.android.synthetic.main.item_article_featured.*


class DetailActivity: AppCompatActivity() {

    var collectionX : MutableList<CollectionX> = ArrayList()
    val test : CollectionX? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_activity)
        inittoolbar()
        collectionX = intent.getParcelableArrayListExtra("detail")

        val position = intent.getIntExtra("position",0)

        titledetail.text = collectionX[position].title
        detail.text = collectionX[position].description
        url.text = collectionX[position].url
        Glide.with(Contextor.getInstance().context).load(collectionX[position].imageUrl).into(detailimg)
    }

    private fun inittoolbar() {
        val toolbar = findViewById<Toolbar>(R.id.toolBarr)
        toolbar.setTitle("Detail")
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        toolbar.setNavigationOnClickListener {
            finish()
        }

    }

}