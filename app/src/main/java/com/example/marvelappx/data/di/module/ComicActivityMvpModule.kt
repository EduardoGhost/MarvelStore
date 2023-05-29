package com.example.marvelappx.data.di.module

import com.example.marvelappx.mvp.Contrato.ListaComicsView
import dagger.Provides
import com.example.marvelappx.data.di.scope.ActivityScope
import dagger.Module

@Module
class ComicActivityMvpModule(private val mView: ListaComicsView) {
    @Provides
    @ActivityScope
    fun provideView(): ListaComicsView {
        return mView
    }
}