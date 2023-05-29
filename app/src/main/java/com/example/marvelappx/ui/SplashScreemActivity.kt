package com.example.marvelappx.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.marvelappx.R
import android.content.Intent
import android.os.Handler
import com.example.marvelappx.ui.ListComics

class SplashScreemActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screem)
        val handler = Handler()
        handler.postDelayed({ mostrarListComics() }, 1)
    }

    private fun mostrarListComics() {
        val intent = Intent(applicationContext, ListComics::class)
        startActivity(intent)
        finish()
    }
}