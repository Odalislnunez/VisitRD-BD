package es.usj.mastertsa.onunez.visitrd.data.repository.api

import com.squareup.moshi.Json

data class PlaceApiModel(
    @field:Json(name = "id") val code:Int,
    @field:Json(name = "name") val name: String,
    @field:Json(name = "location") val location: String,
    @field:Json(name = "description") val description: String,
    @field:Json(name = "images") var images: String,
    @field:Json(name = "latitude") val latitude: String,
    @field:Json(name = "longitude") val longitude: String,
    @field:Json(name = "rating") val rating: Double
)