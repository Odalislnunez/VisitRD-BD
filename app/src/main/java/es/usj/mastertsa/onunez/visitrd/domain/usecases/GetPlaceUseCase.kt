package es.usj.mastertsa.onunez.visitrd.domain.usecases

import es.usj.mastertsa.onunez.visitrd.domain.model.Place
import es.usj.mastertsa.onunez.visitrd.domain.repository.PlaceRepository
import kotlinx.coroutines.flow.Flow

class GetPlaceUseCase(val repository: PlaceRepository) {
    suspend fun getPlaces(): Flow<List<Place>> {
        return repository.getPlaces()
    }
}