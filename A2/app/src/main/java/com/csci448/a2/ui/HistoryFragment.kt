package com.csci448.a2.ui

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.csci448.a2.R
import com.csci448.a2.data.HistoryData

class HistoryFragment:Fragment() {

    interface CallBacks {
        fun onNewGameSelect()
        fun onExitSelect()
        fun onPrefSelect()

    }

    private lateinit var historyViewModel: HistoryViewModel
    private lateinit var adapter: HistoryListAdapter
    private lateinit var historyRecyclerView: RecyclerView

    private var callbacks: CallBacks? = null
    override fun onAttach(context: Context) {
        super.onAttach(context)

        callbacks = context as CallBacks?
    }

    override fun onDetach() {
        super.onDetach()
        callbacks = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)

        val factory = HistoryViewModelFactory(requireContext())
        historyViewModel = ViewModelProvider(this, factory).get(HistoryViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.history_screen_list_fragment,container,false)
        historyRecyclerView = view.findViewById(R.id.history_recycler_view) as RecyclerView
        historyRecyclerView.layoutManager = LinearLayoutManager(context)

        updateUI(emptyList())

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.history_screen_list_fragment,menu)
    }

    //Parse when the different options menus are selected
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.new_game_menu_item) {
            callbacks?.onNewGameSelect()
        }
        else if(item.itemId == R.id.prefrences_menu_item) {
            callbacks?.onPrefSelect()
        }

        else if(item.itemId == R.id.exit_game_menu_item) {
            callbacks?.onExitSelect()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun updateUI(history:List<HistoryData>) {
        adapter = HistoryListAdapter(history)
        historyRecyclerView.adapter = adapter

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        historyViewModel.historyListLiveData.observe(viewLifecycleOwner, Observer { history -> history?.let { updateUI(history) } })
    }
}