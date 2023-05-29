package com.example.marvelappx.data.di.components

import android.content.Context
import com.example.marvelappx.data.di.scope.ApplicationScope
import com.example.marvelappx.data.di.module.ContextModule
import com.example.marvelappx.data.di.module.RetrofitModule
import com.example.marvelappx.data.network.MarvelService
import com.example.marvelappx.data.di.module.ApplicationContexto
import com.example.marvelappx.data.di.Application.MyApplication
import dagger.Component

@ApplicationScope
@Component(modules = [ContextModule::class, RetrofitModule::class])
interface ApplicationComponentes {
    fun marvelService(): MarvelService?

    @get:ApplicationContexto
    val context: Context?
    fun injectApplication(myApplication: MyApplication?)
}