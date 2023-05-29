package com.example.marvelappx.ui

import android.util.Log
import com.example.marvelappx.data.model.Comic.title
import com.example.marvelappx.data.model.Comic.description
import com.example.marvelappx.data.model.Comic.prices
import com.example.marvelappx.data.model.Price.getPrice
import com.example.marvelappx.data.model.Comic.thumbnail
import com.example.marvelappx.data.model.Image.path
import com.example.marvelappx.data.model.Image.extension
import com.example.marvelappx.data.model.Comic.isRare
import javax.inject.Inject
import com.example.marvelappx.ui.ListComicsAdapter.ItemComicClickListener
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelappx.ui.ListComicsAdapter.ListaComicsViewHolder
import com.example.marvelappx.data.model.Comic
import android.widget.TextView
import com.example.marvelappx.R
import com.squareup.picasso.Picasso
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import java.util.*

class ListComicsAdapter @Inject constructor(private val itemComicClickListener: ItemComicClickListener) :
    RecyclerView.Adapter<ListaComicsViewHolder>() {
    private var comics: List<Comic>?

    init {
        comics = ArrayList()
    }

    inner class ListaComicsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textTituloComic: TextView
        private val textDescription: TextView
        private val textPrice: TextView
        private val image: ImageView
        private var comic: Comic? = null
        private val text_rare: TextView

        init {
            textTituloComic = itemView.findViewById(R.id.text_titulo_comic)
            textDescription = itemView.findViewById(R.id.text_Descricao)
            textPrice = itemView.findViewById(R.id.text_Precos)
            image = itemView.findViewById(R.id.image)
            text_rare = itemView.findViewById(R.id.text_rare)
            itemView.setOnClickListener {
                val observavel = Observable.just(comic)
                val observador: Observer<Comic?> = object : Observer<Comic> {
                    override fun onSubscribe(d: Disposable) {}
                    override fun onNext(comic: Comic) {
                        itemComicClickListener.itemComicClicado(comic)
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                        Log.e("", "errou feio")
                    }

                    override fun onComplete() {}
                }
                observavel.subscribe(observador)
                //                    if(itemComicClickListener != null){
//
//                    }
            }
        }

        fun bind(comic: Comic) {
            this.comic = comic
            textTituloComic.text = comic.title
            textDescription.text = comic.description
            textPrice.text = "Preço :" + comic.prices!![0].getPrice()
            Picasso.get().load(
                comic.thumbnail.path + "." +  //imagem .extensão
                        comic.thumbnail.extension
            )
                .placeholder(R.drawable.ic_launcher_background)
                .error(android.R.drawable.btn_dialog)
                .into(image)
            if (comic.isRare) {
                text_rare.text = "Raro"
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListaComicsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_comic, parent, false)
        return ListaComicsViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListaComicsViewHolder, position: Int) {
        holder.bind(comics!![position])
    }

    override fun getItemCount(): Int {
        //return comics.size();
        return if (comics != null && comics!!.size > 0) comics!!.size else 0
    }

    fun setComics(comics: List<Comic>?) {
        this.comics = comics
        notifyDataSetChanged()
    }

    interface ItemComicClickListener {
        fun itemComicClicado(comic: Comic?)
    }
}