package es.usj.mastertsa.onunez.visitrd.presentation.view.states

import es.usj.mastertsa.onunez.visitrd.domain.model.Place

sealed class PlaceState {
    object Loading : PlaceState()
    data class Success(val data: List<Place>) : PlaceState()
    data class Failure(val exception: Throwable) : PlaceState()
}