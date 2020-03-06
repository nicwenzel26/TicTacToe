package com.csci448.a2.ui.History

import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.csci448.a2.R
import com.csci448.a2.data.HistoryData

class HistoryHolder(val view: View):RecyclerView.ViewHolder(view) {

    private lateinit var historyData : HistoryData

    private val winnerTextView: TextView = itemView.findViewById(R.id.winning_player_text_view)
    private val  dateTextView : TextView = itemView.findViewById(R.id.game_date)
    private val loserTextView : TextView = itemView.findViewById(R.id.losing_player_text_view)
    private val tieLinearLayout : LinearLayout = itemView.findViewById(R.id.tie_linear_layout)
    private val winLostLayout : LinearLayout = itemView.findViewById(R.id.win_lose_linear_layout)

    fun bind(historyData: HistoryData) {
        this.historyData = historyData

        if(historyData.didTie) {
            winLostLayout.visibility = View.GONE
            tieLinearLayout.visibility = View.VISIBLE
        }

        winnerTextView.text = this.historyData.loser
        loserTextView.text = this.historyData.winner
        dateTextView.text = this.historyData.date.toString()
    }
    
}