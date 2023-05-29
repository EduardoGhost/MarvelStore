package com.example.marvelappx.data.di.module

import android.content.Context
import com.example.marvelappx.ui.ListComics
import dagger.Provides
import com.example.marvelappx.data.di.scope.ActivityScope
import dagger.Module

@Module
class ActivityContextModule(
    @get:Provides
    @get:ActivityScope val mainActivity: ListComics
) {

    @get:ComicActivityContext
    @get:ActivityScope
    @get:Provides
    var context: Context

    init {
        context = mainActivity
    }
}