package com.csci448.a2.ui

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.csci448.a2.R

class HomeScreenFragment:Fragment() {

    interface CallBacks {
        fun onNewGameSelect()
        fun onPrefSelect()
        fun onExitSelect()
        fun onHistorySelect()

    }

    private var callbacks: CallBacks? = null

    override fun onAttach(context: Context) {
        callbacks = context as CallBacks?
        super.onAttach(context)
    }

    override fun onDetach() {
        callbacks = null
        super.onDetach()
    }

    //Inflate the view for the home screen
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.home_screen_fragment,container,false)
        return view
    }

    //TODO get icons and text for icons in menu bar
    //Inflate the options menu for the home screen
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.home_screen_fragment, menu)
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
        else if(item.itemId == R.id.history_menu_item) {
            callbacks?.onHistorySelect()
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

}