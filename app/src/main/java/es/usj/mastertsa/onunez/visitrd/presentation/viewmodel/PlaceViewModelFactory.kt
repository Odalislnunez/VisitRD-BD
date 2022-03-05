package es.usj.mastertsa.onunez.visitrd.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import es.usj.mastertsa.onunez.visitrd.data.repository.CommentRepositoryImpl
import es.usj.mastertsa.onunez.visitrd.data.repository.PlaceRepositoryImpl
import es.usj.mastertsa.onunez.visitrd.domain.usecases.AddCommentUseCase
import es.usj.mastertsa.onunez.visitrd.domain.usecases.GetCommentUseCase
import es.usj.mastertsa.onunez.visitrd.domain.usecases.GetPlaceUseCase

class PlaceViewModelFactory: ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val repository = PlaceRepositoryImpl()
        val getPlace = GetPlaceUseCase(repository)
        return  PlaceViewModel(getPlace) as T
    }
}