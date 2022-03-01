package es.usj.mastertsa.onunez.visitrd.presentation.view

import es.usj.mastertsa.onunez.visitrd.domain.model.Place

sealed class HomeState {
    object Loading : HomeState()
    data class Success(val data: List<Place>) : HomeState()
    data class Failure(val exception: Throwable) : HomeState()
}