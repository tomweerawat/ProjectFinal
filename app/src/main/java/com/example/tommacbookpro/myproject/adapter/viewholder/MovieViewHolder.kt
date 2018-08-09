package com.example.tommacbookpro.myproject.adapter.viewholder


import android.support.v7.widget.RecyclerView
import android.view.View
import com.bumptech.glide.Glide
import com.example.tommacbookpro.myproject.adapter.MenuHomeAdapter.Companion.clicklistener
import com.example.tommacbookpro.myproject.dao.MovieItemDao
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.store_item_row.*


class MovieViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer, View.OnClickListener  {
    private var view: View = itemView


    init {
        itemView.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        if (clicklistener != null) {
            clicklistener.itemClicked(p0, getAdapterPosition())


        }
    }
    fun bind(post: MovieItemDao) {
        title.text = post.title
        price.text = post.price
        Glide.with(containerView.context).load(post.image).into(thumbnail)


    }
}