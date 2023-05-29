package com.example.marvelappx.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.marvelappx.R
import com.example.marvelappx.data.model.Comic
import android.widget.TextView
import android.content.Intent
import android.os.Handler
import android.view.View
import com.example.marvelappx.ui.ListComics

class FinalActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_final)
        findViewById<View>(R.id.final_imagem).animate().translationXBy(-2000f)
            .setDuration(6000).startDelay = 1000
        findViewById<View>(R.id.cwarr).animate().translationXBy(-2000f)
            .setDuration(6000).startDelay =
            6000
        findViewById<View>(R.id.cwar).animate().translationXBy(-2000f)
            .setDuration(6000).startDelay = 11000
        val handler = Handler()
        handler.postDelayed({ mostrarFinal() }, 20000)
        val total = intent.getStringExtra("keyFinal1")
        val comic = intent.getSerializableExtra("keyFinal2") as Comic?
        val txtTotal = findViewById<TextView>(R.id.text_total_final)
        txtTotal.text = "Novo total: R$: " + String.format("%.4s", total)
    }

    private fun mostrarFinal() {
        val intent = Intent(applicationContext, ListComics::class.java)
        startActivity(intent)
        finish()
    }
}