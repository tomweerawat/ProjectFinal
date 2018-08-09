package com.example.hotumit.tomproject.adapter.viewholder

import android.support.v7.widget.RecyclerView
import android.view.View

import com.example.tommacbookpro.myproject.dao.NewStatusPost
import kotlinx.android.extensions.LayoutContainer

import kotlinx.android.synthetic.main.view_status_post.*

class StatusViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {
    fun bind(post: NewStatusPost) {
        tvTextstatus.text = post.caption
    }
}