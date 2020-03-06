package com.csci448.a2.ui

import androidx.lifecycle.ViewModel
import com.csci448.a2.data.HistoryData
import com.csci448.a2.data.HistoryRepository

class HistoryViewModel(private val historyRepository: HistoryRepository):ViewModel() {
    val historyListLiveData = historyRepository.getHistory()

    fun addGame(historyData: HistoryData) {
        historyRepository.insertGame(historyData)
    }

    fun deleteAll() {
        historyRepository.deleteAll()
    }
    }
