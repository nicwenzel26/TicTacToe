package com.csci448.nwenzel_A2.ui.History

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.csci448.nwenzel_A2.R
import com.csci448.nwenzel_A2.data.HistoryData

class HistoryFragment:Fragment() {
    //Interface for moving between the history screen and the other available screens in the game
    interface CallBacks {
        fun onNewGameSelect()
        fun onExitSelect()
        fun onPrefSelect()

    }
    /*
    LATE INIT ********************************************************************
     */
    private lateinit var historyViewModel: HistoryViewModel
    private lateinit var adapter: HistoryListAdapter
    private lateinit var historyRecyclerView: RecyclerView

    //Set callbacks object to be null initially
    private var callbacks: CallBacks? = null

    //Overriding onAttach() to set the context for the callbacks option
    override fun onAttach(context: Context) {
        super.onAttach(context)
        callbacks = context as CallBacks?
    }

    //Overriding onDetach() to set the callbacks object back to null
    override fun onDetach() {
        super.onDetach()
        callbacks = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Setting the options menu to be visible for this object
        setHasOptionsMenu(true)

        //Getting the factory and setting the view model for the fragment so it can access the data base
        val factory = HistoryViewModelFactory(requireContext())
        historyViewModel = ViewModelProvider(this, factory).get(HistoryViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //Inflate the empty recycle view
        val view = inflater.inflate(R.layout.history_screen_list_fragment,container,false)
        historyRecyclerView = view.findViewById(R.id.history_recycler_view) as RecyclerView
        historyRecyclerView.layoutManager = LinearLayoutManager(context)

        //Update the UI with the adapter for the database
        updateUI(emptyList())

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        //Inflate the options menu XML
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
        //Get the adapter for the database
        adapter = HistoryListAdapter(history)
        historyRecyclerView.adapter = adapter

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        historyViewModel.historyListLiveData.observe(viewLifecycleOwner, Observer { history -> history?.let { updateUI(history) } })
    }
}