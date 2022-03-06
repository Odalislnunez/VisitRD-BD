package es.usj.mastertsa.onunez.visitrd.data.repository.site

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
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
import kotlin.properties.Delegates

const val PLACES_DATABASE = "PlaceDatabase.db"
const val PLACES_DATABASE_VERSION = 1

object PlaceContract {
    object PlaceEntity: BaseColumns {
        val COLUMN_NAME = "name"
        val COLUMN_LOCATION = "location"
        val COLUMN_DESCRIPTION = "description"
        val COLUMN_IMAGES = "images"
        val COLUMN_LATITUDE = "latitude"
        val COLUMN_LONGITUDE = "longitude"
        val COLUMN_RATING = "rating"
        val COLUMN_FAVORITE = "favorite"
        const val TABLE_NAME_PLACE = "places"
    }
    object ImagesPlacesEntity: BaseColumns {
        val COLUMN_PLACE_CODE_I = "place_code"
        val COLUMN_IMAGE = "image"
        val TABLE_NAME_IMAGES = "images_places"
    }
    object CommentsPlacesEntity: BaseColumns {
        val COLUMN_PLACE_CODE_C = "place_code"
        val COLUMN_COMMENT = "comment"
        val COLUMN_USER_NAME = "user_name"
        const val TABLE_NAME_COMMENTS = "comments_places"
    }
}

val CREATE_STATEMENT = "CREATE TABLE $TABLE_NAME_PLACE (" +
        "${BaseColumns._ID} INTEGER PRIMARY KEY," +
        "$COLUMN_NAME TEXT," +
        "$COLUMN_LOCATION TEXT," +
        "$COLUMN_DESCRIPTION TEXT," +
        "$COLUMN_IMAGES TEXT," +
        "$COLUMN_LATITUDE TEXT," +
        "$COLUMN_LONGITUDE TEXT," +
        "$COLUMN_RATING FLOAT," +
        "$COLUMN_FAVORITE TEXT" +
        ") "

val CREATE_STATEMENT_IMAGES = "CREATE TABLE $TABLE_NAME_IMAGES (" +
        "${BaseColumns._ID} INTEGER PRIMARY KEY," +
        "$COLUMN_PLACE_CODE_I INTEGER," +
        "$COLUMN_IMAGE TEXT" +
        ") "

val CREATE_STATEMENTS_COMMENTS = "CREATE TABLE $TABLE_NAME_COMMENTS (" +
        "${BaseColumns._ID} INTEGER PRIMARY KEY," +
        "$COLUMN_PLACE_CODE_C INTEGER," +
        "$COLUMN_COMMENT TEXT," +
        "$COLUMN_USER_NAME TEXT" +
        ") "

val DELETE_PLACE = "DROP TABLE IF EXISTS $TABLE_NAME_PLACE"
val DELETE_IMAGE = "DROP TABLE IF EXISTS $TABLE_NAME_IMAGES"
val DELETE_COMMENT = "DROP TABLE IF EXISTS $TABLE_NAME_COMMENTS"


class PlaceSqLiteHelper(context: Context): SQLiteOpenHelper(context, PLACES_DATABASE, null, PLACES_DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREATE_STATEMENT)
        db?.execSQL(CREATE_STATEMENTS_COMMENTS)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(DELETE_PLACE)
        db?.execSQL(DELETE_COMMENT)
        onCreate(db)
    }
}