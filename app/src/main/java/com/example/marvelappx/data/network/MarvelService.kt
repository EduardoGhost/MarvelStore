package com.example.marvelappx.data.network

import retrofit2.http.GET
import com.example.marvelappx.data.network.response.ComicDataWrapper
import io.reactivex.Observable
import retrofit2.http.Query

interface MarvelService {
    @GET("/v1/public/comics")
    fun getAllComics(
        @Query("ts") ts: String?,  //time stamp igual 1
        @Query("apikey") apikey: String?,
        @Query("hash") hash: String?,
        @Query("limit") limit: String?,
        @Query("formatType") formatType: String?
    ): Observable<ComicDataWrapper?>?
}