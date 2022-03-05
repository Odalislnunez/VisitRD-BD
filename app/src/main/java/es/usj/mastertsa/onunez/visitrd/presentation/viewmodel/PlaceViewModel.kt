package es.usj.mastertsa.onunez.visitrd.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import es.usj.mastertsa.onunez.visitrd.domain.model.Place
import es.usj.mastertsa.onunez.visitrd.domain.usecases.GetPlaceUseCase
import es.usj.mastertsa.onunez.visitrd.presentation.view.states.PlaceState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PlaceViewModel(
    val getPlaceUseCase: GetPlaceUseCase
): ViewModel() {
    private val placesMutableStateFlow: MutableStateFlow<PlaceState> =
        MutableStateFlow(PlaceState.Loading)
    val homeStateFlow: StateFlow<PlaceState> = placesMutableStateFlow

    fun getData() {
        viewModelScope.launch {
            delay(1000)
            val newPlaces = getPlaceUseCase.getPlaces()
            placesMutableStateFlow.emit(PlaceState.Success(newPlaces))
        }
    }
}