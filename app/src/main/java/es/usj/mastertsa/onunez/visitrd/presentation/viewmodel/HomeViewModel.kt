package es.usj.mastertsa.onunez.visitrd.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import es.usj.mastertsa.onunez.visitrd.domain.model.Place
import es.usj.mastertsa.onunez.visitrd.presentation.view.states.PlaceState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel: ViewModel() {
    private val placesMutableStateFlow: MutableStateFlow<PlaceState> =
        MutableStateFlow(PlaceState.Loading)
    val homeStateFlow: StateFlow<PlaceState> = placesMutableStateFlow

    fun getData() {
        viewModelScope.launch {
            delay(1000)
            val places = getFakeData()
//            val place = Place(0,
//                "Prueba",
//                "Prueba",
//                "Prueba",
//                listOf<String>("\"https://lh5.googleusercontent.com/p/AF1QipM6m4VuHejk_PRf2N41zLcyJG_4FxAx6cl2DZ54=w1080-k-no\",\n" +
//                        "     \"https://gringosinthedr.files.wordpress.com/2017/12/img-7954.jpg?w=1200\""),
//                "https://www.alltrails.com/trail/dominican-republic/azua/sendero-cola-de-pato",
//                "",
//                "",
//                4.5)
            placesMutableStateFlow.emit(PlaceState.Success(places))
        }
    }

    fun getFakeData(): List<Place> {
        val places = mutableListOf<Place>()
        places.add(Place(0,
                "Prueba",
                "Prueba",
                "Prueba",
                listOf<String>("\"https://lh5.googleusercontent.com/p/AF1QipM6m4VuHejk_PRf2N41zLcyJG_4FxAx6cl2DZ54=w1080-k-no\",\n" +
                        "     \"https://gringosinthedr.files.wordpress.com/2017/12/img-7954.jpg?w=1200\""),
                "https://www.alltrails.com/trail/dominican-republic/azua/sendero-cola-de-pato",
                "",
                "",
                4.5,
                false)
        )
        places.add(Place(1,
                "Prueba",
                "Prueba",
                "Prueba",
                listOf<String>("\"https://lh5.googleusercontent.com/p/AF1QipM6m4VuHejk_PRf2N41zLcyJG_4FxAx6cl2DZ54=w1080-k-no\",\n" +
                        "     \"https://gringosinthedr.files.wordpress.com/2017/12/img-7954.jpg?w=1200\""),
                "https://www.alltrails.com/trail/dominican-republic/azua/sendero-cola-de-pato",
                "",
                "",
                4.5,
                true)
        )

        return places
    }
}