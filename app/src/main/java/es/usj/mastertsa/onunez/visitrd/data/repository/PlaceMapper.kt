package es.usj.mastertsa.onunez.visitrd.data.repository

import es.usj.mastertsa.onunez.visitrd.data.repository.api.PlaceApiModel
import es.usj.mastertsa.onunez.visitrd.data.repository.room.CommentDbModel
import es.usj.mastertsa.onunez.visitrd.data.repository.room.PlaceDbModel
import es.usj.mastertsa.onunez.visitrd.domain.model.Comment
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

    fun mapPlaceFromDbToDomain(placeDbModel: PlaceDbModel): Place {
        return Place(
            placeDbModel.code,
            placeDbModel.name,
            placeDbModel.location,
            placeDbModel.description,
            placeDbModel.images,
            placeDbModel.latitude,
            placeDbModel.longitude,
            placeDbModel.rating,
            placeDbModel.favorite
        )
    }

    fun mapDbToDomainFromPlace(place: Place): PlaceDbModel {
        return PlaceDbModel(
            place.code,
            place.name,
            place.location,
            place.description,
            place.images,
            place.latitude,
            place.longitude,
            place.rating,
            place.favorite
        )
    }

    fun mapCommentFromDbToDomain(commentDbModel: CommentDbModel): Comment {
        return Comment(
            commentDbModel.id,
            commentDbModel.place_code,
            commentDbModel.comment,
            commentDbModel.user_name
        )
    }

    fun mapDbToDomainFromComment(comment: Comment): CommentDbModel {
        return CommentDbModel(
            comment.id,
            comment.place_code,
            comment.comment,
            comment.user_name
        )
    }
}