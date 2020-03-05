package com.csci448.a2.ui

import androidx.lifecycle.ViewModel
import com.csci448.a2.data.HistoryData

class HistoryViewModel:ViewModel() {
    val history = mutableListOf<HistoryData>()

    init {
        for(i in 0 until 100) {
            val historyItem = HistoryData()
            history += historyItem
        }
    }
}