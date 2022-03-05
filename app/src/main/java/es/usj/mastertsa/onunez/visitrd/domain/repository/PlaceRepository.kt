package es.usj.mastertsa.onunez.visitrd.domain.repository

import es.usj.mastertsa.onunez.visitrd.domain.model.Place

interface PlaceRepository {
    fun getPlaces(): List<Place>
}