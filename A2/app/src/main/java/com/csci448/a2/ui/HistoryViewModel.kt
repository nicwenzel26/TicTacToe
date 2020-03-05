package com.csci448.a2.ui

import androidx.lifecycle.ViewModel
import com.csci448.a2.data.HistoryData
import com.csci448.a2.data.HistoryRepository

class HistoryViewModel(private val historyRepository: HistoryRepository):ViewModel() {
    val history = historyRepository.getHistory()
    }
