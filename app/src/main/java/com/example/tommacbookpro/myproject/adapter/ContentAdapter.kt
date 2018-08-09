package com.example.tommacbookpro.myproject.adapter


import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.tommacbookpro.myproject.constant.Conts

import com.example.tommacbookpro.myproject.adapter.viewholder.PhotoViewHolder
import com.example.hotumit.tomproject.adapter.viewholder.StatusViewHolder

import com.example.tommacbookpro.myproject.dao.NewPhotoItemDao
import com.example.tommacbookpro.myproject.dao.NewStatusPost
import com.example.tommacbookpro.myproject.dao.Post
import com.example.tommacbookpro.myproject.R
import com.example.tommacbookpro.myproject.utility.ClickListener


class ContentAdapter : ListAdapter<Post, RecyclerView.ViewHolder>(PostDiffCallback()) {
    companion object {
        public lateinit var clicklistener: ClickListener
    }
    fun setClickListener(listener: ClickListener) {
        clicklistener = listener
    }

    override fun getItemViewType(position: Int): Int {
    /*  if (position%2==0){
          Log.e("position", "position" + position)


      }else{
          return getItem(position).type
      }*/
        return getItem(position).type
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = when (viewType) {
        Conts.STATUS -> StatusViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_status_post, parent, false)) as RecyclerView.ViewHolder
        Conts.PHOTO -> PhotoViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_photo_post, parent, false)) as RecyclerView.ViewHolder
        else -> throw NullPointerException("View holder for type $viewType not found")
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if (holder is StatusViewHolder) {
            val statusPost = getItem(position) as NewStatusPost
            holder.bind(statusPost)
        } else if (holder is PhotoViewHolder) {
            val photoPost = getItem(position) as NewPhotoItemDao
            holder.bind(photoPost)
        }
    }
}