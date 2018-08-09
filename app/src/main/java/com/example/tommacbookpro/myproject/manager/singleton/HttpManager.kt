package com.example.tommacbookpro.myproject.manager.singleton


import com.example.tommacbookpro.myproject.constant.Conts
import com.example.tommacbookpro.myproject.manager.http.ApiService
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit




object HttpManager {


    fun ApiService(): ApiService {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient
                .Builder()
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .addInterceptor(interceptor)
                .build()

        val gson = GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .setPrettyPrinting()
                .create()
        val retrofit = Retrofit.Builder()
                .client(client)
                .baseUrl(Conts.Base_Url)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()

        return retrofit.create(ApiService::class.java)
    }

    fun MovieService(): ApiService {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient
                .Builder()
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .addInterceptor(interceptor)
                .build()

        val gson = GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .setPrettyPrinting()
                .create()
        val retrofit = Retrofit.Builder()
                .client(client)
                .baseUrl(Conts.BaseMovie)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()

        return retrofit.create(ApiService::class.java)
    }
    fun DessertService(): ApiService {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient
                .Builder()
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .addInterceptor(interceptor)
                .build()

        val gson = GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .setPrettyPrinting()
                .create()
        val retrofit = Retrofit.Builder()
                .client(client)
                .baseUrl(Conts.BaseDessert)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()

        return retrofit.create(ApiService::class.java)
    }

}
