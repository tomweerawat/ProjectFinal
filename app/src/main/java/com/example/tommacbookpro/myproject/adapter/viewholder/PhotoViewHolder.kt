package com.example.tommacbookpro.myproject.adapter.viewholder

import android.support.v7.widget.RecyclerView
import android.view.View

import com.bumptech.glide.Glide
import com.example.tommacbookpro.myproject.adapter.ContentAdapter.Companion.clicklistener

import com.example.tommacbookpro.myproject.dao.NewPhotoItemDao

import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.view_photo_post.*

class PhotoViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer, View.OnClickListener  {


    init {
        itemView.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        if (clicklistener != null) {
            clicklistener.itemClicked(p0, getAdapterPosition())


        }
    }

    fun bind(post: NewPhotoItemDao) {
        tvCodeName.text = post.caption
        tvReleaseDate.text = post.timestamp
        Glide.with(containerView.context).load(post.url).into(img)
    }
}