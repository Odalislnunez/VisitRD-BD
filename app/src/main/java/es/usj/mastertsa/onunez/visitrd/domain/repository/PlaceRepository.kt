package es.usj.mastertsa.onunez.visitrd.domain.repository

import es.usj.mastertsa.onunez.visitrd.domain.model.Images
import es.usj.mastertsa.onunez.visitrd.domain.model.Place

interface PlaceRepository {
    suspend fun getPlaces(): List<Place>

    fun addPlaces(place: Place)
}