package com.example.seller.models

import com.google.gson.annotations.SerializedName

data class ListingDataModel(
    @SerializedName("results")
    val results: ArrayList<results>
)

data class results(
    @SerializedName("created_at")
    val created: String,
    @SerializedName("price")
    val price: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("uid")
    val uid: String,
    @SerializedName("image_ids")
    val imageID: ArrayList<String>,
    @SerializedName("image_urls")
    val imageUrl: ArrayList<String>,
    @SerializedName("image_urls_thumbnails")
    val imageUrlThumbs: ArrayList<String>

)
