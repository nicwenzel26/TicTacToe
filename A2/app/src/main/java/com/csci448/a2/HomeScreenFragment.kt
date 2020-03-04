package com.csci448.a2

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment

class HomeScreenFragment:Fragment() {

    interface CallBacks {

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.home_screen_fragment,container,false)
        return view
    }

    //TODO get icons and text for icons in menu bar
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.home_screen_fragment, menu)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

}