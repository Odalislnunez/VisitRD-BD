package es.usj.mastertsa.onunez.visitrd.presentation.view

import es.usj.mastertsa.onunez.visitrd.domain.model.Place

sealed class MainState {
    object Loading : MainState()
    data class Success(val place: Place) : MainState()
    data class Failure(val exception: Throwable) : MainState()
}