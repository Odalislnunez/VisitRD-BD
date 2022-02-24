package es.usj.mastertsa.onunez.visitrd.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.libraries.places.api.model.Place
import es.usj.mastertsa.onunez.visitrd.presentation.view.MainState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(): ViewModel() {
    private val mainMutableStateFlow: MutableStateFlow<MainState> =
        MutableStateFlow(MainState.Loading)
    val mainStateFlow: StateFlow<MainState> = mainMutableStateFlow
    fun getData() {
        viewModelScope.launch {
            delay(3000) //Simulating network request
            val place = Place("", "", "", [""], "", "", "", 0.00)
            mainMutableStateFlow.emit(MainState.Success(place))
        }
    }
}