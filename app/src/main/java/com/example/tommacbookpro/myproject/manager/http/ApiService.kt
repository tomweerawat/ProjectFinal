package com.example.tommacbookpro.myproject.manager.http



import com.example.tommacbookpro.myproject.dao.DessertItemDao
import com.example.tommacbookpro.myproject.dao.MovieItemDao
import com.example.tommacbookpro.myproject.dao.PhotoItemCollectionDao

import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @POST("list")
    fun loadPhotoList(): Call<PhotoItemCollectionDao>

    @GET("movies_2017.json")
    fun loadMovieList():Call<List<MovieItemDao>>

    @Headers("user-key: 7cd5f10c66378443bb51b7346136680c")
    @POST("api/v2.1/collections")
    fun loadDessertList(@Query("city_id")city_id: Int): Call<DessertItemDao>

    @POST("list/after/{id}")
    fun loadPhotoListAfterId(@Path("id") id: Int): Call<PhotoItemCollectionDao>

    @POST("list/before/{id}")
    fun loadPhotoListBeforeId(@Path("id") id: Int): Call<PhotoItemCollectionDao>




}
