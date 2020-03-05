package com.csci448.a2.data

import android.content.Context
import java.util.*

class HistoryRepository(private val historyDao: HistoryDao) {

    fun getHistory():List<HistoryData> = historyDao.getHistory()
    fun getHistoryData(id:UUID):HistoryData? = historyDao.getHistoryData(id)

    companion object{
        private var instance: HistoryRepository? = null

        fun getInstance(context: Context): HistoryRepository? {
            return instance ?: let {
                if(instance == null) {
                    val database = HistoryDatabase.getInstance(context)
                    instance = HistoryRepository(database.historyDao())
                }

                return instance
            }
        }
    }
}