package es.usj.mastertsa.onunez.visitrd.data.repository.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [PlaceDbModel::class, CommentDbModel::class], version = 1, exportSchema = true)
abstract class PlaceDatabase: RoomDatabase() {

    abstract fun getDaoPlace(): PlaceDao
    abstract fun getDaoComment(): CommentDao

    companion object {
        @Volatile
        private var INSTANCE: PlaceDatabase? = null

        fun getDatabase(context: Context): PlaceDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context, PlaceDatabase::class.java,
                    "PlacesRoom.db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}