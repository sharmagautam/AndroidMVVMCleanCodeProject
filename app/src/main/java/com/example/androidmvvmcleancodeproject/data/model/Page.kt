package com.example.androidmvvmcleancodeproject.data.model


import com.google.gson.annotations.SerializedName

data class Page(
    @SerializedName("audio")
    val audio: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("description_read")
    val descriptionRead: String,
    @SerializedName("image_url")
    val imageUrl: String,
    @SerializedName("page_count")
    val pageCount: String,
    @SerializedName("sub_title")
    val subTitle: String,
    @SerializedName("sub_title_colour")
    val subTitleColour: String,
    @SerializedName("sub_title_font")
    val subTitleFont: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("title_colour")
    val titleColour: String,
    @SerializedName("title_font")
    val titleFont: String,
    @SerializedName("title_style")
    val titleStyle: String
)