package es.usj.mastertsa.onunez.visitrd.domain.usecases

import es.usj.mastertsa.onunez.visitrd.domain.model.Place
import es.usj.mastertsa.onunez.visitrd.domain.repository.PlaceRepository

class GetPlaceUseCase(val repository: PlaceRepository) {
    fun getPlaces(): List<Place> {
        return repository.getPlaces()
    }
}