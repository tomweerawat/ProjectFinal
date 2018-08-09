package com.example.tommacbookpro.myproject.dao

import com.google.gson.annotations.SerializedName

data class DessertItemDao(
        @SerializedName("collections") val collections: List<Collection>,
        @SerializedName("has_more") val hasMore: Int,
        @SerializedName("share_url") val shareUrl: String,
        @SerializedName("display_text") val displayText: String,
        @SerializedName("has_total") val hasTotal: Int
)