package com.example.marvelappx.data.di.module

import android.content.Context
import dagger.Provides
import com.example.marvelappx.data.di.scope.ApplicationScope
import dagger.Module

@Module
class ContextModule(var context: Context) {
    @Provides
    @ApplicationScope
    @ApplicationContexto
    fun context(): Context {
        return context
    }
}