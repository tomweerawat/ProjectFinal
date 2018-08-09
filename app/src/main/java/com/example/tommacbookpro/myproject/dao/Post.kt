package com.example.tommacbookpro.myproject.dao

import android.os.Parcel
import android.os.Parcelable

open class Post(open var id: String, open var caption: String, open var timestamp: String, var type: Int) : Parcelable {
    constructor(source: Parcel) : this(
            source.readString(),
            source.readString(),
            source.readString(),
            source.readInt()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(id)
        writeString(caption)
        writeString(timestamp)
        writeInt(type)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Post> = object : Parcelable.Creator<Post> {
            override fun createFromParcel(source: Parcel): Post = Post(source)
            override fun newArray(size: Int): Array<Post?> = arrayOfNulls(size)
        }
    }
}
