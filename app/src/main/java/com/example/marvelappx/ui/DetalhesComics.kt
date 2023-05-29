package com.example.marvelappx.ui

import com.example.marvelappx.data.model.Comic.title
import com.example.marvelappx.data.model.Comic.description
import com.example.marvelappx.data.model.Comic.prices
import com.example.marvelappx.data.model.Price.getPrice
import com.example.marvelappx.data.model.Comic.thumbnail
import com.example.marvelappx.data.model.Image.path
import com.example.marvelappx.data.model.Image.extension
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.marvelappx.R
import com.example.marvelappx.data.model.Comic
import android.widget.TextView
import com.squareup.picasso.Picasso
import android.widget.EditText
import android.content.Intent
import android.view.View
import android.widget.Button
import android.widget.ImageView
import com.example.marvelappx.ui.PdfActivity
import com.example.marvelappx.ui.VideoViewActivity
import com.example.marvelappx.ui.CheckoutActivity
import java.io.Serializable

class DetalhesComics : AppCompatActivity() {
    private var buttonC: Button? = null
    private var buttonVideo: Button? = null
    private var buttonPDF: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhes_comics)

        // Intent i = getIntent();
        val comic = intent.getSerializableExtra("key") as Comic?
        val txtTitulo = findViewById<TextView>(R.id.text_comic)
        txtTitulo.text = comic!!.title
        val descriçao = findViewById<TextView>(R.id.text_Descricao2)
        descriçao.text = """
            Descrição
            ${comic.description}
            """.trimIndent()
        val preco = findViewById<TextView>(R.id.text_Precos_2)
        preco.text = "Preço: " + comic.prices!![0].getPrice().toString()
        val image = findViewById<ImageView>(R.id.image2)
        Picasso.get().load(
            comic.thumbnail.path + "." +  //imagem .extensão
                    comic.thumbnail.extension
        )
            .placeholder(R.drawable.ic_launcher_background)
            .error(android.R.drawable.btn_dialog)
            .into(image)
        val total = findViewById<TextView>(R.id.text_total)
        buttonC = findViewById<View>(R.id.buttonC) as Button
        buttonVideo = findViewById<View>(R.id.buttonVideo) as Button
        buttonPDF = findViewById<View>(R.id.buttonPDF) as Button


        //quantidade
        val editText = findViewById<EditText>(R.id.edit)
        editText.setText("")
        buttonPDF!!.setOnClickListener {
            val intent = Intent(applicationContext, PdfActivity::class.java)
            startActivity(intent)
        }
        buttonVideo!!.setOnClickListener {
            val intent = Intent(applicationContext, VideoViewActivity::class.java)
            startActivity(intent)
        }
        buttonC!!.setOnClickListener { //preco.setText("Preço: " + String.valueOf(comic.getPrices().get(0).getPrice()));
            val intent = Intent(this@DetalhesComics, CheckoutActivity::class.java)
            intent.putExtra("keyy", editText.text.toString())
            intent.putExtra("keyx", comic as Serializable?)
            startActivity(intent)
        }
    }
}