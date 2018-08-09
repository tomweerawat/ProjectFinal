package com.example.tommacbookpro.myproject.dao

import com.example.tommacbookpro.myproject.dao.CollectionX
import com.google.gson.annotations.SerializedName

data class Collection(
    @SerializedName("collection") val collection: CollectionX
)