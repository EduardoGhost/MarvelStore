package com.example.marvelappx.data.network.response

import com.example.marvelappx.data.model.Comic
import com.google.gson.annotations.SerializedName
import com.example.marvelappx.data.network.response.ComicDataContainer

class ComicDataContainer(@field:SerializedName("results") val results: List<Comic>)