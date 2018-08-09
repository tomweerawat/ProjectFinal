package com.example.tommacbookpro.myproject

import android.app.Application
import android.content.Context
import com.example.tommacbookpro.myproject.utility.Contextor


class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        Contextor.getInstance().init(applicationContext)

    }
    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)

    }

}
