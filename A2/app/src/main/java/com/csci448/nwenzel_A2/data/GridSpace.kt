package com.csci448.nwenzel_A2.data

import android.widget.ImageButton

/*
Data class to hold the information for each avalible space on a Tic Tac Toe Board
Holds the Image Button associated with the space, the current value (x or o) initially set to null
and gives the position of the grid so it can be remade from a saved instance state.
 */
data class GridSpace(val button: ImageButton, var xOrO :Char? = null, val position: Int)