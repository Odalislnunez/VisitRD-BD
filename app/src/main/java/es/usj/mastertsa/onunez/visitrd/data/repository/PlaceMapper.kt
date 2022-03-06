package es.usj.mastertsa.onunez.visitrd.data.repository

import es.usj.mastertsa.onunez.visitrd.data.repository.api.PlaceApiModel
import es.usj.mastertsa.onunez.visitrd.domain.model.Place

object PlaceMapper {

    fun mapPlaceFromApiToDomain(placeApiModel: PlaceApiModel): Place {
        return Place(
            placeApiModel.code,
            placeApiModel.name,
            placeApiModel.location,
            placeApiModel.description,
            placeApiModel.images,
            placeApiModel.latitude,
            placeApiModel.longitude,
            placeApiModel.rating,
            "false"
        )
    }
}