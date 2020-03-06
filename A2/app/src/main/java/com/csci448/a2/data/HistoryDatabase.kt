package com.csci448.a2.data

import android.content.Context
import androidx.room.*

//SQLite table name
private const val DATABASE_NAME = "history-database"

//Saying the database should be made of HistoryData values
@Database(entities = [HistoryData::class], version = 1)

@TypeConverters(HistoryConverters::class)
abstract class HistoryDatabase: RoomDatabase() {
    //Connecting the Dao
    abstract fun historyDao() : HistoryDao

    override fun clearAllTables() {

    }

    companion object {
        private var instance: HistoryDatabase? = null

        fun getInstance(context: Context): HistoryDatabase {
            return instance ?: let {
                instance?: Room.databaseBuilder(context, HistoryDatabase::class.java, DATABASE_NAME).build()
            }
        }
    }
}