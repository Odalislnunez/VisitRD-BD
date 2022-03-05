package es.usj.mastertsa.onunez.visitrd.data.repository

import es.usj.mastertsa.onunez.visitrd.domain.model.Comment
import es.usj.mastertsa.onunez.visitrd.domain.model.Place
import es.usj.mastertsa.onunez.visitrd.domain.repository.PlaceRepository

class PlaceRepositoryImpl: PlaceRepository {
    private val places = getFakeData()

    override fun getPlaces(): List<Place> {
        val newPlaces = mutableListOf<Place>()
        newPlaces.addAll(places)

        return newPlaces
    }

    fun getFakeData(): MutableList<Place> {
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