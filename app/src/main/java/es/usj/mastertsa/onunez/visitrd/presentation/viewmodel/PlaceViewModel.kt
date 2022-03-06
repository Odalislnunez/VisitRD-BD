package es.usj.mastertsa.onunez.visitrd.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import es.usj.mastertsa.onunez.visitrd.domain.model.Place
import es.usj.mastertsa.onunez.visitrd.domain.usecases.AddPlaceUseCase
import es.usj.mastertsa.onunez.visitrd.domain.usecases.GetPlaceUseCase
import es.usj.mastertsa.onunez.visitrd.presentation.view.states.PlaceState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class PlaceViewModel(
    private val getPlaceUseCase: GetPlaceUseCase,
    private val addPlaceUseCase: AddPlaceUseCase
): ViewModel() {
    private val placesMutableStateFlow: MutableStateFlow<PlaceState> =
        MutableStateFlow(PlaceState.Loading)
    val homeStateFlow: StateFlow<PlaceState> = placesMutableStateFlow

    fun getData() {
        viewModelScope.launch {
            getPlaceUseCase.getPlaces().collect { placeList ->
                placesMutableStateFlow.emit(PlaceState.Success(placeList))
            }
        }
    }

    fun addPlace(place: Place) {
        viewModelScope.launch {
            addPlaceUseCase.addPlace(place)
        }
    }
}