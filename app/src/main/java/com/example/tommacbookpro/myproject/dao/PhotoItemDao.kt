package com.example.tommacbookpro.myproject.dao

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class PhotoItemDao(
        var type: Int,
        @SerializedName("id") val id: Int,
        @SerializedName("link") val link: String,
        @SerializedName("image_url") val imageUrl: String,
        @SerializedName("caption") val caption: String,
        @SerializedName("user_id") val userId: Int,
        @SerializedName("username") val username: String,
        @SerializedName("profile_picture") val profilePicture: String,
        @SerializedName("tags") val tags: List<String>,
        @SerializedName("created_time") val createdTime: String,
        @SerializedName("camera") val camera: String,
        @SerializedName("lens") val lens: String,
        @SerializedName("focal_length") val focalLength: String,
        @SerializedName("iso") val iso: String,
        @SerializedName("shutter_speed") val shutterSpeed: String,
        @SerializedName("aperture") val aperture: String
) : Parcelable {
    constructor(source: Parcel) : this(
            source.readInt(),
            source.readInt(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readInt(),
            source.readString(),
            source.readString(),
            source.createStringArrayList(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeInt(type)
        writeInt(id)
        writeString(link)
        writeString(imageUrl)
        writeString(caption)
        writeInt(userId)
        writeString(username)
        writeString(profilePicture)
        writeStringList(tags)
        writeString(createdTime)
        writeString(camera)
        writeString(lens)
        writeString(focalLength)
        writeString(iso)
        writeString(shutterSpeed)
        writeString(aperture)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<PhotoItemDao> = object : Parcelable.Creator<PhotoItemDao> {
            override fun createFromParcel(source: Parcel): PhotoItemDao = PhotoItemDao(source)
            override fun newArray(size: Int): Array<PhotoItemDao?> = arrayOfNulls(size)
        }
    }
}
