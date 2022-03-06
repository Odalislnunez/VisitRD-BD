package es.usj.mastertsa.onunez.visitrd.data.repository.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import es.usj.mastertsa.onunez.visitrd.data.repository.site.PlaceContract.CommentsPlacesEntity.TABLE_NAME_COMMENTS
import kotlinx.coroutines.flow.Flow

@Dao
interface CommentDao {

    @Insert
    suspend fun insertComment(commentDbModel: CommentDbModel)

    @Query("SELECT * FROM $TABLE_NAME_COMMENTS WHERE PLACE_CODE = :placeCode")
    fun getComments(placeCode: Int): Flow<List<CommentDbModel>>
}