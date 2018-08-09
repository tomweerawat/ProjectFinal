package com.example.tommacbookpro.myproject.dao

import android.os.Parcel
import android.os.Parcelable
import com.example.tommacbookpro.myproject.constant.Conts
import com.google.gson.annotations.SerializedName

data class CollectionX(
        /*   val type :Int,*/
        @SerializedName("collection_id") val collectionId: Int,
        @SerializedName("res_count") val resCount: Int,
        @SerializedName("image_url") val imageUrl: String,
        @SerializedName("url") val url: String,
        @SerializedName("title") val title: String,
        @SerializedName("description") val description: String,
        @SerializedName("share_url") val shareUrl: String
) : TypeForRecyclerview(Conts.Dessert), Parcelable {
    constructor(source: Parcel) : this(
            source.readInt(),
            source.readInt(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeInt(collectionId)
        writeInt(resCount)
        writeString(imageUrl)
        writeString(url)
        writeString(title)
        writeString(description)
        writeString(shareUrl)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<CollectionX> = object : Parcelable.Creator<CollectionX> {
            override fun createFromParcel(source: Parcel): CollectionX = CollectionX(source)
            override fun newArray(size: Int): Array<CollectionX?> = arrayOfNulls(size)
        }
    }
}