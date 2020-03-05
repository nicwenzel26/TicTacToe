package com.csci448.a2.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

class HistoryFragment:Fragment() {

    private lateinit var historyViewModel: HistoryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val factory = HistoryViewModelFactory()
        historyViewModel = ViewModelProvider(this, factory).get(HistoryViewModel::class.java)
    }
}