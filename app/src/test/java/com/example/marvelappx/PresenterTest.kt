import android.widget.ImageView
import com.example.marvelappx.R
import com.example.marvelappx.data.model.Comic
import com.example.marvelappx.data.model.Image
import com.example.marvelappx.data.network.MarvelService
import com.example.marvelappx.data.network.response.ComicDataWrapper
import com.example.marvelappx.mvp.Contrato
import com.example.marvelappx.mvp.Presenter
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import java.util.Collections.list

class PresenterTest {

    @Mock
    lateinit var marvelService: MarvelService

    @Mock
    lateinit var mView: Contrato.ListaComicsView

    lateinit var presenter: Presenter

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        presenter = Presenter(marvelService, mView)
    }


    @Test
    fun testObterComic() {

        val comicDataWrapper = ComicDataWrapper(comicList)
        Mockito.when(marvelService.getAllComics( "1",
            "87eae2cc29e0e5c27e1978b9b1d484f5",
            "fddd12b1cc463430b1ef5e4853f20b8a",
            "10",
            "collection")).thenReturn(Single.just(comicDataWrapper))

        presenter.obterComic()


        verify(mView).exibirComics(anyList())
    }


    val comicList = listOf(
        Comic("Spider", "aranha verso e etc", Image("thumbnail",".png"),false)
    )
}


