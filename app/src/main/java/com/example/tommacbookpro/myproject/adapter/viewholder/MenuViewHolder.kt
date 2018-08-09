package com.example.hotumit.tomproject.adapter.viewholder

import android.support.v7.widget.RecyclerView
import android.view.View


import com.example.tommacbookpro.myproject.adapter.MenuHomeAdapter.Companion.clicklistener
import com.example.tommacbookpro.myproject.dao.MenuItemDao



import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.view_item_menu.*


class MenuViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer, View.OnClickListener  {
    private var view: View = itemView


    init {
        itemView.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        if (clicklistener != null) {
            clicklistener.itemClicked(p0, getAdapterPosition())


        }
    }
    fun bind(post: MenuItemDao) {
        newcusreg.text = post.txt


    }
}