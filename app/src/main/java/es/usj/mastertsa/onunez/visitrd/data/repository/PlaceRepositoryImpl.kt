package es.usj.mastertsa.onunez.visitrd.data.repository

import android.content.ContentValues
import android.content.Context
import android.provider.BaseColumns
import android.util.Log
import androidx.core.content.contentValuesOf
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import es.usj.mastertsa.onunez.visitrd.data.repository.api.PlaceService
import es.usj.mastertsa.onunez.visitrd.domain.model.Place
import es.usj.mastertsa.onunez.visitrd.domain.repository.PlaceRepository
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import es.usj.mastertsa.onunez.visitrd.data.repository.PlaceKey.timestamp
import es.usj.mastertsa.onunez.visitrd.data.repository.room.PlaceDao
import es.usj.mastertsa.onunez.visitrd.data.repository.room.PlaceDbModel
import es.usj.mastertsa.onunez.visitrd.data.repository.site.CREATE_STATEMENT
import es.usj.mastertsa.onunez.visitrd.data.repository.site.DELETE_PLACE
import es.usj.mastertsa.onunez.visitrd.data.repository.site.PlaceContract.CommentsPlacesEntity.COLUMN_COMMENT
import es.usj.mastertsa.onunez.visitrd.data.repository.site.PlaceContract.CommentsPlacesEntity.COLUMN_PLACE_CODE_C
import es.usj.mastertsa.onunez.visitrd.data.repository.site.PlaceContract.CommentsPlacesEntity.COLUMN_USER_NAME
import es.usj.mastertsa.onunez.visitrd.data.repository.site.PlaceContract.CommentsPlacesEntity.TABLE_NAME_COMMENTS
import es.usj.mastertsa.onunez.visitrd.data.repository.site.PlaceContract.ImagesPlacesEntity.COLUMN_IMAGE
import es.usj.mastertsa.onunez.visitrd.data.repository.site.PlaceContract.ImagesPlacesEntity.COLUMN_PLACE_CODE_I
import es.usj.mastertsa.onunez.visitrd.data.repository.site.PlaceContract.ImagesPlacesEntity.TABLE_NAME_IMAGES
import es.usj.mastertsa.onunez.visitrd.data.repository.site.PlaceContract.PlaceEntity.COLUMN_DESCRIPTION
import es.usj.mastertsa.onunez.visitrd.data.repository.site.PlaceContract.PlaceEntity.COLUMN_FAVORITE
import es.usj.mastertsa.onunez.visitrd.data.repository.site.PlaceContract.PlaceEntity.COLUMN_IMAGES
import es.usj.mastertsa.onunez.visitrd.data.repository.site.PlaceContract.PlaceEntity.COLUMN_LATITUDE
import es.usj.mastertsa.onunez.visitrd.data.repository.site.PlaceContract.PlaceEntity.COLUMN_LOCATION
import es.usj.mastertsa.onunez.visitrd.data.repository.site.PlaceContract.PlaceEntity.COLUMN_LONGITUDE
import es.usj.mastertsa.onunez.visitrd.data.repository.site.PlaceContract.PlaceEntity.COLUMN_NAME
import es.usj.mastertsa.onunez.visitrd.data.repository.site.PlaceContract.PlaceEntity.COLUMN_RATING
import es.usj.mastertsa.onunez.visitrd.data.repository.site.PlaceContract.PlaceEntity.TABLE_NAME_PLACE
import es.usj.mastertsa.onunez.visitrd.data.repository.site.PlaceSqLiteHelper
import es.usj.mastertsa.onunez.visitrd.domain.model.Comment
import es.usj.mastertsa.onunez.visitrd.domain.model.Images
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

const val TIMESTAMP = "TimeStamp"
const val FIVE_DAYS = 432000000
const val PLACES_DATA_STORE = "PlaceDataStore"

val Context.dataStore by preferencesDataStore(
    name = PLACES_DATA_STORE
)

object PlaceKey {
    val timestamp = longPreferencesKey(TIMESTAMP)
}

class PlaceRepositoryImpl(
    private val dataStore: DataStore<Preferences>,
    private val placeSqLiteHelper: PlaceSqLiteHelper,
    private val placeService: PlaceService,
    private val placeDao: PlaceDao
): PlaceRepository {
    private val db = placeSqLiteHelper.writableDatabase

    private suspend fun shouldRefresh(): Boolean {
        val timestamp = dataStore.data.map { preference ->
            preference[timestamp] ?: 0
        }.first()

        return System.currentTimeMillis() - timestamp > FIVE_DAYS
    }

    override suspend fun getPlaces(): Flow<List<Place>> {
        if(shouldRefresh()){
            placeService.getPlaces().forEach { placeApiModel ->
                val place = PlaceMapper.mapPlaceFromApiToDomain(placeApiModel)
                addPlaces(place)
            }
        }

        dataStore.edit { mutablePreferences ->
            mutablePreferences[timestamp] = System.currentTimeMillis()
        }

        return placeDao.getPlaces().map { placeList ->
            placeList.map { place -> PlaceMapper.mapPlaceFromDbToDomain(place) }
        }

        /*val places = mutableListOf<Place>()
//        val images = mutableListOf<Images>()

        val cursor = db.rawQuery("SELECT * FROM $TABLE_NAME_PLACE", null)
        Log.d("PLACE_REPOSITORY", Thread.currentThread().name)

        with(cursor) {
            while (moveToNext()) {
                val code = getInt(getColumnIndexOrThrow(BaseColumns._ID))
                val name = getString(getColumnIndexOrThrow(COLUMN_NAME))
                val location = getString(getColumnIndexOrThrow(COLUMN_LOCATION))
                val description = getString(getColumnIndexOrThrow(COLUMN_DESCRIPTION))
                val images = getString(getColumnIndexOrThrow(COLUMN_IMAGES))
                val latitude = getString(getColumnIndexOrThrow(COLUMN_LATITUDE))
                val longitude = getString(getColumnIndexOrThrow(COLUMN_LONGITUDE))
                val rating = getDouble(getColumnIndexOrThrow(COLUMN_RATING))
                val favorite = getString(getColumnIndexOrThrow(COLUMN_FAVORITE))

                places.add(Place(code, name, location, description, images, latitude, longitude, rating, favorite))
            }
        }

        return places*/
    }

    override suspend fun addPlaces(place: Place) {
        val placeToAdd = PlaceMapper.mapDbToDomainFromPlace(place)
        placeDao.insertPlace(placeToAdd)

        /*val placeContentValues = ContentValues()
        placeContentValues.put(BaseColumns._ID, place.code)
        placeContentValues.put(COLUMN_NAME, place.name)
        placeContentValues.put(COLUMN_LOCATION, place.location)
        placeContentValues.put(COLUMN_DESCRIPTION, place.description)
        placeContentValues.put(COLUMN_IMAGES, place.images)
        placeContentValues.put(COLUMN_LATITUDE, place.latitude)
        placeContentValues.put(COLUMN_LONGITUDE, place.longitude)
        placeContentValues.put(COLUMN_RATING, place.rating)
        placeContentValues.put(COLUMN_FAVORITE , place.favorite)

        db.insert(TABLE_NAME_PLACE, null, placeContentValues)*/
    }
}