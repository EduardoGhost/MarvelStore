package com.example.marvelappx.mvp

import android.util.Log
import javax.inject.Inject
import com.example.marvelappx.data.network.MarvelService
import com.example.marvelappx.mvp.Contrato.ListaComicsView
import com.example.marvelappx.mvp.Contrato.ListaComicsPresenter
import com.example.marvelappx.data.network.response.ComicDataWrapper
import io.reactivex.schedulers.Schedulers
import io.reactivex.android.schedulers.AndroidSchedulers
import com.example.marvelappx.data.model.Comic
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import java.sql.DriverManager.println
import java.util.*
import java.util.Random


class Presenter @Inject constructor(
    var marvelService: MarvelService,
    private var mView: ListaComicsView?
) : ListaComicsPresenter {
    override fun obterComic() {
        Log.i("CHAMADO ONCREATE", "obter a Lista")
        val observavel = marvelService
            .getAllComics(
                "1",
                "87eae2cc29e0e5c27e1978b9b1d484f5",
                "fddd12b1cc463430b1ef5e4853f20b8a",
                "10",
                "collection"
            )
        observavel
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<ComicDataWrapper> {
                override fun onSubscribe(d: Disposable) {}
                override fun onNext(comicDataWrapper: ComicDataWrapper) {
                    val listaComics = filtro(comicDataWrapper.data.results)
                    val newList = randomRare(listaComics)
                    //                            //List<Comic> newList = comicRare(listaComics);
                    //Collections.shuffle(newList);
//                            //view.exibirComics(listaComics);
                    mView!!.exibirComics(newList)
                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()
                    Log.e("entrou no failure", "errou feio")
                    mView!!.mostrarErro()
                }

                override fun onComplete() {}
            })
    }

    override fun destruirView() {
        mView = null
    }

    private fun filtro(comics: List<Comic>): List<Comic> {
        val filtroComics: MutableList<Comic> = ArrayList()
        for (comic in comics) {
            if (!comic.thumbnail.url.contains(IMG_NOT_AVAILABLE)) {
                filtroComics.add(comic)
            }
        }
        return filtroComics
    }

    fun comicRare(comicRare: List<Comic?>): List<Comic?> {
        Collections.shuffle(comicRare)
        var cont = 0
        val x = comicRare.size
        while (cont != x) {
            if (comicRare[cont]!!.isRare == false) {
                comicRare[cont]!!.isRare = true
                println("RARO: " + comicRare[cont])
                break
            }
            cont++
            println("CONT: $cont")
        }
        return comicRare
    }

    fun randomRare(comicRandom: List<Comic>): List<Comic> {
        val random = Random()
        for (i in 0 until comicRandom.size - 1) {
            val j = random.nextInt(comicRandom.size)
            comicRandom[j].isRare = true
            break
        }
        return comicRandom
    }

    companion object {
        const val IMG_NOT_AVAILABLE = "image_not_available"
    }
}