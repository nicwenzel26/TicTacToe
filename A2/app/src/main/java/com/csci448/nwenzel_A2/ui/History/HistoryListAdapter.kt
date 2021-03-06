package com.csci448.nwenzel_A2.ui.History

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.csci448.nwenzel_A2.R
import com.csci448.nwenzel_A2.data.HistoryData

class HistoryListAdapter(private val history: List<HistoryData>): RecyclerView.Adapter<HistoryHolder>() {
    override fun getItemCount(): Int {
        return history.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryHolder {
        val view =  LayoutInflater.from(parent.context).inflate(R.layout.list_item_history,parent,false)
        return HistoryHolder(view)
    }

    override fun onBindViewHolder(holder: HistoryHolder, position: Int) {
        val historyItem = history[position]

        holder.bind(historyItem)
    }

}