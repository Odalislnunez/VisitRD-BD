package es.usj.mastertsa.onunez.visitrd.data.repository

import android.content.ContentValues
import android.provider.BaseColumns
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import es.usj.mastertsa.onunez.visitrd.data.repository.api.PlaceService
import es.usj.mastertsa.onunez.visitrd.data.repository.site.PlaceContract
import es.usj.mastertsa.onunez.visitrd.data.repository.site.PlaceContract.CommentsPlacesEntity.COLUMN_COMMENT
import es.usj.mastertsa.onunez.visitrd.data.repository.site.PlaceContract.CommentsPlacesEntity.COLUMN_PLACE_CODE_C
import es.usj.mastertsa.onunez.visitrd.data.repository.site.PlaceContract.CommentsPlacesEntity.COLUMN_USER_NAME
import es.usj.mastertsa.onunez.visitrd.data.repository.site.PlaceContract.CommentsPlacesEntity.TABLE_NAME_COMMENTS
import es.usj.mastertsa.onunez.visitrd.data.repository.site.PlaceSqLiteHelper
import es.usj.mastertsa.onunez.visitrd.domain.model.Comment
import es.usj.mastertsa.onunez.visitrd.domain.repository.CommentRepository

class CommentRepositoryImpl(
    private val placeSqLiteHelper: PlaceSqLiteHelper
): CommentRepository {
    private val db = placeSqLiteHelper.writableDatabase

    override fun getComments(placeCode: Int): List<Comment> {
        val comments = mutableListOf<Comment>()

        val cursor_comments = db.rawQuery("SELECT * FROM $TABLE_NAME_COMMENTS WHERE PLACE_CODE = $placeCode", null)

        with(cursor_comments) {
            while (moveToNext()) {
                val id = getInt(getColumnIndexOrThrow(BaseColumns._ID))
                val place_code = getInt(getColumnIndexOrThrow(PlaceContract.CommentsPlacesEntity.COLUMN_PLACE_CODE_C))
                val comment = getString(getColumnIndexOrThrow(PlaceContract.CommentsPlacesEntity.COLUMN_COMMENT))
                val user_name = getString(getColumnIndexOrThrow(PlaceContract.CommentsPlacesEntity.COLUMN_USER_NAME))

                comments.add(Comment(id, place_code, comment, user_name))
            }
        }

        return comments
    }

    override fun addComments(comment: Comment) {
        val commentContentValues = ContentValues()
        commentContentValues.put(BaseColumns._ID, comment.id)
        commentContentValues.put(COLUMN_PLACE_CODE_C, comment.place_code)
        commentContentValues.put(COLUMN_COMMENT, comment.comment)
        commentContentValues.put(COLUMN_USER_NAME, comment.user_name)

        db.insert(TABLE_NAME_COMMENTS, null, commentContentValues)
    }
}