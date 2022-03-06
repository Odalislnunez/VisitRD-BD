package es.usj.mastertsa.onunez.visitrd.data.repository.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import es.usj.mastertsa.onunez.visitrd.data.repository.site.PlaceContract.CommentsPlacesEntity.TABLE_NAME_COMMENTS

@Entity(tableName = TABLE_NAME_COMMENTS)
data class CommentDbModel (
    @PrimaryKey
    val id: Int,
    val place_code: Int,
    val comment: String,
    val user_name: String
)