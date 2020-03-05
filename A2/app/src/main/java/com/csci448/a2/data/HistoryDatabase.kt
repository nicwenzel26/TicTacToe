package com.csci448.a2.data

import android.content.Context
import androidx.room.*

private const val DATABASE_NAME = "history-database"

@Database(entities = [HistoryData::class], version = 1)
@TypeConverters(HistoryConverters::class)
abstract class HistoryDatabase: RoomDatabase() {
    companion object {
        private var instance: HistoryDatabase? = null

        fun getInstance(context: Context): HistoryDatabase {
            return instance ?: let {
                instance?: Room.databaseBuilder(context, HistoryDatabase::class.java, DATABASE_NAME).build()
            }
        }
    }
}