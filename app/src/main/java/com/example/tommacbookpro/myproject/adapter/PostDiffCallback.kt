package com.example.tommacbookpro.myproject.adapter

import android.support.v7.util.DiffUtil
import com.example.tommacbookpro.myproject.dao.Post


class PostDiffCallback : DiffUtil.ItemCallback<Post>() {
    override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
       /* if (oldItem is PhotoItemDao && newItem is PhotoItemDao) {
            return oldItem.username == newItem.username && oldItem.createdTime == newItem.createdTime
        }
        return false*/
        return oldItem.id == newItem.id
    }
}