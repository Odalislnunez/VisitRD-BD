package es.usj.mastertsa.onunez.visitrd.domain.repository

import es.usj.mastertsa.onunez.visitrd.domain.model.Images
import es.usj.mastertsa.onunez.visitrd.domain.model.Place
import kotlinx.coroutines.flow.Flow

interface PlaceRepository {
    suspend fun getPlaces(): Flow<List<Place>>

    suspend fun addPlaces(place: Place)
}