package es.usj.mastertsa.onunez.visitrd.domain.usecases

import es.usj.mastertsa.onunez.visitrd.domain.model.Images
import es.usj.mastertsa.onunez.visitrd.domain.model.Place
import es.usj.mastertsa.onunez.visitrd.domain.repository.PlaceRepository

class AddPlaceUseCase(val repository: PlaceRepository) {
    fun addPlace(place: Place) {
        repository.addPlaces(place)
    }
}