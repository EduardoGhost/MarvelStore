package com.example.marvelappx.mvp

import com.example.marvelappx.data.model.Comic

interface Contrato {
    interface ListaComicsView {
        fun exibirComics(comics: List<Comic?>?)
        fun mostrarErro()
    }

    interface ListaComicsPresenter {
        fun obterComic()
        fun destruirView()
    }
}