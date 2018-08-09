package com.example.tommacbookpro.myproject.dao

import com.example.tommacbookpro.myproject.dao.PhotoItemDao
import com.google.gson.annotations.SerializedName
data class PhotoItemCollectionDao(
        @SerializedName("success") val success: Boolean,
        @SerializedName("data") val data: ArrayList<PhotoItemDao>
)