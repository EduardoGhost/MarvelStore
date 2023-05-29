package com.example.marvelappx.data.di.Application

import android.app.Activity
import android.app.Application
import com.example.marvelappx.data.di.components.ApplicationComponentes
import com.example.marvelappx.data.di.components.DaggerApplicationComponentes
import com.example.marvelappx.data.di.module.ContextModule

class MyApplication : Application() {
    var applicationComponentes: ApplicationComponentes? = null
    override fun onCreate() {
        super.onCreate()
        applicationComponentes =
            DaggerApplicationComponentes.builder().contextModule(ContextModule(this)).build()
        applicationComponentes.injectApplication(this)
    }

    val applicationComponent: ApplicationComponentes?
        get() = applicationComponentes

    companion object {
        operator fun get(activity: Activity): MyApplication {
            return activity.getApplication() as MyApplication
        }
    }
}