package com.csci448.nwenzel_A2.data

import android.content.Context
import androidx.lifecycle.LiveData
import java.util.*
import java.util.concurrent.Executors

class HistoryRepository(private val historyDao: HistoryDao) {
    private val executor = Executors.newSingleThreadExecutor()

    //Implementing DAO operations
    fun getHistory():LiveData<List<HistoryData>> = historyDao.getHistory()
    fun getHistoryData(id:UUID):LiveData<HistoryData?> = historyDao.getHistoryData(id)
    fun insertGame(historyData: HistoryData) {
        executor.execute{
            historyDao.insertGame(historyData)
        }
    }
    fun deleteAll()  {
        executor.execute {
            historyDao.deleteAll()
        }
    }


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