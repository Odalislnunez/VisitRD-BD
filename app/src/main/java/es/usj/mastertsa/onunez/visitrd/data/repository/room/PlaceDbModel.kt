package es.usj.mastertsa.onunez.visitrd.data.repository.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import es.usj.mastertsa.onunez.visitrd.data.repository.site.PlaceContract.PlaceEntity.TABLE_NAME_PLACE
import java.io.Serializable

@Entity(tableName = TABLE_NAME_PLACE)
data class PlaceDbModel(
    @PrimaryKey
    val code:Int,
    val name: String,
    val location: String,
    val description: String,
    val images: String,
    val latitude: String,
    val longitude: String,
    val rating: Double,
    var favorite: String?
) : Serializable