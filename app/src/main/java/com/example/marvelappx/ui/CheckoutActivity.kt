package com.example.marvelappx.ui

import com.example.marvelappx.data.model.Comic.title
import com.example.marvelappx.data.model.Comic.thumbnail
import com.example.marvelappx.data.model.Image.path
import com.example.marvelappx.data.model.Image.extension
import com.example.marvelappx.data.model.Comic.prices
import com.example.marvelappx.data.model.Price.getPrice
import com.example.marvelappx.data.model.Comic.isRare
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.marvelappx.R
import com.example.marvelappx.data.model.Comic
import com.squareup.picasso.Picasso
import android.content.Intent
import android.view.View
import android.widget.*
import com.example.marvelappx.ui.FinalActivity
import java.io.Serializable
import java.sql.DriverManager.println

class CheckoutActivity : AppCompatActivity() {
    private var btnConfirmarCompra: Button? = null
    private var btnAplicarCopum: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.checkout_activity)
        val check = findViewById<TextView>(R.id.text_checkout)
        check.text = "CHECKOUT"
        val quantidade = intent.getStringExtra("keyy")
        val comic = intent.getSerializableExtra("keyx") as Comic?
        val txtTitulo = findViewById<TextView>(R.id.text_comic)
        txtTitulo.text = comic!!.title
        val image = findViewById<ImageView>(R.id.image2)
        Picasso.get().load(
            comic.thumbnail.path + "." +  //imagem .extensão
                    comic.thumbnail.extension
        )
            .placeholder(R.drawable.ic_launcher_background)
            .error(android.R.drawable.btn_dialog)
            .into(image)
        val txtTotal = findViewById<TextView>(R.id.text_totalC)
        val txtQuanti = findViewById<TextView>(R.id.text_quantidade)
        txtQuanti.text = "Quantidade: $quantidade"
        val valorTotal = comic.prices!![0].getPrice().toFloat()
        val quantiConvertido: Int = quantidade.toInt()
        val total = valorTotal * quantiConvertido
        txtTotal.text = "Valor total: R$: $total"
        val editText = findViewById<EditText>(R.id.edit_cupom)
        editText.setText("")
        btnConfirmarCompra = findViewById<View>(R.id.btnConfirmarCompra) as Button
        btnAplicarCopum = findViewById<View>(R.id.btnAplicarCupom) as Button
        btnConfirmarCompra!!.setOnClickListener {
            val cupom = editText.text.toString()
            if (cupom.contains("comum") && !comic.isRare) {
                val intent = Intent(applicationContext, FinalActivity::class)
                val desconto10 = total / 100 * 10
                val novoTotalComum = total - desconto10
                intent.putExtra("keyFinal1", novoTotalComum.toString())
                intent.putExtra("keyFinal2", comic as Serializable?)
                startActivity(intent)
                Toast.makeText(
                    btnAplicarCopum!!.context, "Cupom de 10% aplicado",
                    Toast.LENGTH_SHORT
                ).show()
            } else if (cupom.contains("raro") && comic.isRare == true) {
                val intent = Intent(applicationContext, FinalActivity::class)
                println("CUPOM RARO $cupom")
                val desconto25 = total / 100 * 25
                val novoTotalRaro = total - desconto25
                intent.putExtra("keyFinal1", novoTotalRaro.toString())
                intent.putExtra("keyFinal2", comic as Serializable?)
                startActivity(intent)
                Toast.makeText(
                    btnAplicarCopum!!.context,
                    "Cupom de 25% aplicado",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                val intent = Intent(applicationContext, FinalActivity::class)
                intent.putExtra("keyFinal1", total.toString())
                startActivity(intent)
                Toast.makeText(
                    btnAplicarCopum!!.context, "Cupom invalido",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}