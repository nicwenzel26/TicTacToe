package com.csci448.nwenzel_A2.ui.History

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.csci448.nwenzel_A2.data.HistoryRepository

class HistoryViewModelFactory(private val context: Context): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(HistoryRepository::class.java).newInstance(HistoryRepository.getInstance(context))
    }
}