package es.usj.mastertsa.onunez.visitrd.data.repository.api

import retrofit2.http.GET

interface PlaceService {

    @GET("/SPlaces.json")
    suspend fun getPlaces(): List<PlaceApiModel>
}