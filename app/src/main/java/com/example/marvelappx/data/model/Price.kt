package com.example.marvelappx.data.model

import com.google.gson.annotations.SerializedName
import com.example.marvelappx.data.model.Price
import java.io.Serializable

class Price(var type: String, private var price: Double) : Serializable {
    fun getPrice(): Double {
        if (price == 0.0) {
            price = 9.99
        }
        return price
    }

    fun setPrice(price: Double) {
        this.price = price
    }
}