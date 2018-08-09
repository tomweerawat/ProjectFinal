package com.example.tommacbookpro.myproject.dao.dummydata

import com.example.tommacbookpro.myproject.dao.MenuItemDao

object MenuGenerator {

    fun createAndroidVersionInfo(): MutableList<MenuItemDao> {
        return arrayListOf(
                MenuItemDao(txt = "Picture"),
                MenuItemDao(txt = "Movie"),
                MenuItemDao(txt = "All"),
                MenuItemDao(txt = "Dummy")


        )
    }
}