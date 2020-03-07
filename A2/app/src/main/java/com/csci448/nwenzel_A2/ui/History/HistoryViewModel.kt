package com.csci448.nwenzel_A2.ui.History

import androidx.lifecycle.ViewModel
import com.csci448.nwenzel_A2.data.HistoryData
import com.csci448.nwenzel_A2.data.HistoryRepository

class HistoryViewModel(private val historyRepository: HistoryRepository):ViewModel() {
    val historyListLiveData = historyRepository.getHistory()

    fun addGame(historyData: HistoryData) {
        historyRepository.insertGame(historyData)
    }

    fun deleteAll() {
        historyRepository.deleteAll()
    }
    }
