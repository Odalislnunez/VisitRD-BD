package es.usj.mastertsa.onunez.visitrd

import java.io.Serializable

class Place(
    val name: String, val location: String, val description: String, var images: List<String>, var comments: List<Comment>?,
    val latitude: String, val longitude: String, val rating: Double
) : Serializable