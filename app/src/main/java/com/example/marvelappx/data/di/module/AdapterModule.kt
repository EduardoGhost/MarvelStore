package com.example.marvelappx.data.di.module

import dagger.Provides
import com.example.marvelappx.data.di.scope.ActivityScope
import com.example.marvelappx.ui.ListComicsAdapter.ItemComicClickListener
import com.example.marvelappx.ui.ListComicsAdapter
import com.example.marvelappx.ui.ListComics
import dagger.Module

@Module(includes = [ActivityContextModule::class])
class AdapterModule {
    @Provides
    @ActivityScope
    fun getCoinList(itemComicClickListener: ItemComicClickListener?): ListComicsAdapter {
        return ListComicsAdapter(itemComicClickListener)
    }

    //
    @Provides
    @ActivityScope
    fun getClickListener(listComics: ListComics): ItemComicClickListener {
        return listComics
    }
}