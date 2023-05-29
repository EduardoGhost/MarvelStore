package com.example.marvelappx.data.di.module

import okhttp3.OkHttpClient.Builder.connectTimeout
import okhttp3.OkHttpClient.Builder.readTimeout
import okhttp3.OkHttpClient.Builder.addInterceptor
import okhttp3.OkHttpClient.Builder.build
import com.example.marvelappx.mvp.Contrato.ListaComicsView
import dagger.Provides
import com.example.marvelappx.data.di.scope.ActivityScope
import com.example.marvelappx.data.di.scope.ApplicationScope
import com.example.marvelappx.data.di.module.ApplicationContexto
import com.example.marvelappx.data.di.module.ContextModule
import retrofit2.Retrofit
import com.example.marvelappx.data.network.MarvelService
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber
import okhttp3.OkHttpClient
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import java.util.concurrent.TimeUnit
import javax.inject.Named

@Module(includes = [ContextModule::class])
class RetrofitModule {
    var mBaseUrl = "https://gateway.marvel.com/v1/public/"
    @Provides
    @ApplicationScope
    fun marvelService(retrofit: Retrofit): MarvelService {
        return retrofit.create(MarvelService::class)
    }

    @Provides
    @ApplicationScope
    fun provideGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    @ApplicationScope
    @Named("ok-2")
    fun httpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor =
            HttpLoggingInterceptor { message: String? -> Timber.i("deu certo") }
        return httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    @ApplicationScope
    @Named("ok-1")
    fun provideOkHttpClient1(@Named("ok-2") httpLoggingInterceptor: HttpLoggingInterceptor?): OkHttpClient {
        return Builder()
            .connectTimeout(300, TimeUnit.SECONDS)
            .readTimeout(300, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    @Provides
    @ApplicationScope
    fun provideRetrofit(
        @Named("ok-1") client: OkHttpClient?,
        converterFactory: GsonConverterFactory?
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(mBaseUrl)
            .addConverterFactory(converterFactory)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
            .build()
    }
}