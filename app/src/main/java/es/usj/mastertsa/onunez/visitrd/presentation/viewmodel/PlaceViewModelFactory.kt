package es.usj.mastertsa.onunez.visitrd.presentation.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import es.usj.mastertsa.onunez.visitrd.data.repository.CommentRepositoryImpl
import es.usj.mastertsa.onunez.visitrd.data.repository.PlaceRepositoryImpl
import es.usj.mastertsa.onunez.visitrd.data.repository.api.PlaceService
import es.usj.mastertsa.onunez.visitrd.data.repository.dataStore
import es.usj.mastertsa.onunez.visitrd.data.repository.site.PlaceSqLiteHelper
import es.usj.mastertsa.onunez.visitrd.domain.usecases.AddCommentUseCase
import es.usj.mastertsa.onunez.visitrd.domain.usecases.AddPlaceUseCase
import es.usj.mastertsa.onunez.visitrd.domain.usecases.GetCommentUseCase
import es.usj.mastertsa.onunez.visitrd.domain.usecases.GetPlaceUseCase
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import kotlin.coroutines.coroutineContext

class PlaceViewModelFactory(private val context: Context): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val sqLiteHelper = PlaceSqLiteHelper(context)
        val repository = PlaceRepositoryImpl(context.dataStore, sqLiteHelper, createService())
        val getPlace = GetPlaceUseCase(repository)
        val addPlace = AddPlaceUseCase(repository)
        return  PlaceViewModel(getPlace, addPlace) as T
    }

    private fun createService(): PlaceService {
        val retrofit = Retrofit
            .Builder()
            .baseUrl("https://visitrd-ios.000webhostapp.com")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        return retrofit.create(PlaceService::class.java)
    }
}