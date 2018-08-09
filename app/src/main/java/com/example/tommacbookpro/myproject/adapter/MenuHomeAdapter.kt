package com.example.tommacbookpro.myproject.adapter

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.tommacbookpro.myproject.constant.Conts
import com.example.hotumit.tomproject.adapter.viewholder.MenuViewHolder
import com.example.tommacbookpro.myproject.dao.MenuItemDao
import com.example.tommacbookpro.myproject.dao.dummydata.ImageDummyData
import com.example.tommacbookpro.myproject.R
import com.example.tommacbookpro.myproject.utility.ClickListener

import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.view_item_menu.view.*


class MenuHomeAdapter(private val androidList: MutableList<MenuItemDao>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object {
        public lateinit var clicklistener: ClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        Log.e("Menuvt","Menuvt"+viewType)
        if (viewType == Conts.Menu) {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.view_item_menu, parent, false)
            return MenuViewHolder(view)
        }
        throw NullPointerException("View holder for type $viewType not found")
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        Picasso.with(holder.itemView.getContext())
                .load(ImageDummyData.data[position])
                .centerCrop()
                .resize(240, 240)
                .into(holder.itemView.logoimg)

        when (position) {
            1 -> {
                holder.itemView.mCardViewBottom.setCardBackgroundColor(Color.parseColor("#b9f5ca"))
                holder.itemView.lnbg.setBackgroundColor((Color.parseColor("#1de9b6")))

            }
            2 -> {
                holder.itemView.mCardViewBottom.setCardBackgroundColor(Color.parseColor("#fefe8d"))
                holder.itemView.lnbg.setBackgroundColor((Color.parseColor("#ffea3b")))
                holder.itemView.newcusreg.setTextColor(Color.parseColor("#475151"))
            }
            3 -> {
                holder.itemView.mCardViewBottom.setCardBackgroundColor(Color.parseColor("#84ffff"))
                holder.itemView.lnbg.setBackgroundColor((Color.parseColor("#03a9f4")))
            }
        }
        if (holder is MenuViewHolder) {
            (holder as MenuViewHolder).bind(androidList!![position])
           /* (holder as MenuViewHolder).bind(androidList!![position])*/
        }
    }

    override fun getItemCount(): Int {
        return androidList.size
    }
    override fun getItemViewType(position: Int): Int {

        return androidList[position].type
    }
    fun setClickListener(listener: ClickListener) {
        clicklistener = listener
    }
}