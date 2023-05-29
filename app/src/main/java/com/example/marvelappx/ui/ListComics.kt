package com.example.marvelappx.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelappx.R
import com.example.marvelappx.data.di.Application.MyApplication
import com.example.marvelappx.data.di.components.ApplicationComponentes
import com.example.marvelappx.data.di.components.ComicActivityComponent
import com.example.marvelappx.data.di.components.DaggerComicActivityComponent
import com.example.marvelappx.data.di.module.ActivityContextModule
import com.example.marvelappx.data.di.module.ApplicationContexto
import com.example.marvelappx.data.di.module.ComicActivityContext
import com.example.marvelappx.data.di.module.ComicActivityMvpModule
import com.example.marvelappx.data.model.Comic
import com.example.marvelappx.mvp.Contrato
import com.example.marvelappx.mvp.Presenter
import javax.inject.Inject

/** MyMain */
class ListComics : AppCompatActivity(), Contrato.ListaComicsView,
    ListComicsAdapter.ItemComicClickListener {
    var comicActivityComponent: ComicActivityComponent? = null

    @Inject
    var Adapter: ListComicsAdapter? = null

    @Inject
    @ApplicationContexto
    var mContext: Context? = null

    @Inject
    @ComicActivityContext
    var activityContext: Context? = null

    @Inject
    var presenter: Presenter? = null
    protected override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_comics)
        val applicationComponentes: ApplicationComponentes =
            MyApplication.get(this).getApplicationComponent()
        comicActivityComponent = DaggerComicActivityComponent.builder()
            .activityContextModule(ActivityContextModule(this))
            .comicActivityMvpModule(ComicActivityMvpModule(this))
            .applicationComponentes(applicationComponentes)
            .build()
        comicActivityComponent.injectMainActivity(this)
        presenter.obterComic()
        //presenter = new Presenter(this);
        configAdapter()
    }

    private fun configAdapter() {
        val recyclerComics: RecyclerView = findViewById<RecyclerView>(R.id.recycler_comics)
        Adapter = ListComicsAdapter(this)
        val linearLayoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this@ListComics)
        recyclerComics.setLayoutManager(linearLayoutManager)
        recyclerComics.setAdapter(Adapter)
    }

    override fun exibirComics(comics: List<Comic?>?) {
        Adapter!!.setComics(comics)
    }

    override fun mostrarErro() {
        Toast.makeText(this@ListComics, "Não rodou!", Toast.LENGTH_SHORT).show()
        Log.e(TAG, "Não rodou")
    }

    protected override fun onDestroy() {
        super.onDestroy()
        presenter.destruirView()
    }

    override fun itemComicClicado(comic: Comic) {
        val intent = Intent(this@ListComics, DetalhesComics::class.java)
        intent.putExtra("key", comic)
        startActivity(intent)
    }

    companion object {
        private const val TAG = "ListComics"
    }
}