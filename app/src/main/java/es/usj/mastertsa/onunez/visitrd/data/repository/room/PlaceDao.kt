package es.usj.mastertsa.onunez.visitrd.data.repository.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import es.usj.mastertsa.onunez.visitrd.data.repository.site.PlaceContract.PlaceEntity.TABLE_NAME_PLACE
import kotlinx.coroutines.flow.Flow

@Dao
interface PlaceDao {

    @Insert
    suspend fun insertPlace(placeDbModel: PlaceDbModel)

    @Query("SELECT * FROM $TABLE_NAME_PLACE")
    fun getPlaces(): Flow<List<PlaceDbModel>>
}