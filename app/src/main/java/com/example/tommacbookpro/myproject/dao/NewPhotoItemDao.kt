package com.example.tommacbookpro.myproject.dao


import com.example.tommacbookpro.myproject.constant.Conts


class NewPhotoItemDao( id: String,  caption: String,  timestamp: String, var url: String) : Post(id, caption, timestamp, Conts.PHOTO)