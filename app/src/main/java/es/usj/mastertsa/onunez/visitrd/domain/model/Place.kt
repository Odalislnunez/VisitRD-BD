package es.usj.mastertsa.onunez.visitrd.domain.model

import java.io.Serializable

data class Place(
    val code:Int, val name: String, val location: String, val description: String, val images: String,
    val latitude: String, val longitude: String, val rating: Double, var favorite: String?
) : Serializable