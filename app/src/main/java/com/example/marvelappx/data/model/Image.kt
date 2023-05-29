package com.example.marvelappx.data.model

import com.google.gson.annotations.SerializedName
import com.example.marvelappx.data.model.Price
import java.io.Serializable

//miniatura das imagens
class Image(
    @field:SerializedName("path") val path: String, @field:SerializedName(
        "extension"
    ) val extension: String
) : Serializable {
    val RESOLUTION = "/portrait_fantastic"

    val url: String
        get() = "$path$RESOLUTION.$extension"
}