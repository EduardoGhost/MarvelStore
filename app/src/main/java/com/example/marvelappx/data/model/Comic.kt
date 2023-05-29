package com.example.marvelappx.data.model

import com.google.gson.annotations.SerializedName
import com.example.marvelappx.data.model.Price
import java.io.Serializable

class Comic : Serializable {
    @SerializedName("title")
    var title: String
        private set

    @SerializedName("description")
    var description: String
        private set

    @SerializedName("thumbnail")
    var thumbnail: Image
        private set

    @SerializedName("prices")
    var prices: List<Price>? = null
        private set
    var isRare: Boolean

    constructor(
        title: String,
        description: String,
        thumbnail: Image,
        prices: List<Price>?,
        rare: Boolean
    ) {
        this.title = title
        this.description = description
        this.thumbnail = thumbnail
        this.prices = prices
        isRare = rare
    }

    constructor(title: String, description: String, thumbnail: Image, rare: Boolean) {
        this.title = title
        this.description = description
        this.thumbnail = thumbnail
        isRare = rare
    }
}