package com.john.islamiv2.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.john.islamiv2.Models.Sura
import com.john.islamiv2.Utils.Constants

@Database(entities = [Sura::class], version = 1, exportSchema = true)
abstract class SurasDatabase : RoomDatabase() {
    abstract fun surasDao(): SurasDao

    companion object {
        @Volatile
        private var instance: SurasDatabase? = null
        fun initDatabase(context: Context){
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context,
                    SurasDatabase::class.java,
                    Constants.SURAS_DATABASE_NAME
                ).fallbackToDestructiveMigration().allowMainThreadQueries().build()
            }
        }
        fun getDatabase(): SurasDatabase {
            return instance!!
        }
    }
}