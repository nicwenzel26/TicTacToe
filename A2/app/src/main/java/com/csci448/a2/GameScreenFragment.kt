package com.csci448.a2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.fragment.app.Fragment

class GameScreenFragment:Fragment() {

    private lateinit var button_11 : ImageButton
    private lateinit var button_12 : ImageButton
    private lateinit var button_13 : ImageButton
    private lateinit var button_21 : ImageButton
    private lateinit var button_22 : ImageButton
    private lateinit var button_23 : ImageButton
    private lateinit var button_31 : ImageButton
    private lateinit var button_32 : ImageButton
    private lateinit var button_33 : ImageButton

    private lateinit var grid_11: GridSpace
    private lateinit var grid_12: GridSpace
    private lateinit var grid_13: GridSpace
    private lateinit var grid_21: GridSpace
    private lateinit var grid_22: GridSpace
    private lateinit var grid_23: GridSpace
    private lateinit var grid_31: GridSpace
    private lateinit var grid_32: GridSpace
    private lateinit var grid_33: GridSpace




    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.game_screen_fragment, container, false)

        initButtons(view)

        initGrid()

        initOnClickGrid()

        return view
    }
    //Check if space is used. If yes tell the user, if no assign their piece
    private fun checkSpace(gridSpace: GridSpace) {
        if(gridSpace.xOrO == null) {
            gridSpace.button.setImageResource(R.drawable.x)
            gridSpace.xOrO = 'x'
            checkWIn()
        }

        else {
            Toast.makeText(context, "That space is already taken", Toast.LENGTH_SHORT).show()
        }
    }

    private fun checkWIn() {
        if(grid_11.xOrO == 'x' && grid_12.xOrO == 'x' && grid_13.xOrO == 'x' ) {
            Toast.makeText(context, "X wins", Toast.LENGTH_SHORT).show()
        }
    }


    //Init buttons for all the grid spots
    private fun initButtons(view: View) {
        button_11 = view.findViewById(R.id.button_11)
        button_12 = view.findViewById(R.id.button_12)
        button_13 = view.findViewById(R.id.button_13)
        button_21 = view.findViewById(R.id.button_21)
        button_22 = view.findViewById(R.id.button_22)
        button_23 = view.findViewById(R.id.button_23)
        button_31 = view.findViewById(R.id.button_31)
        button_32 = view.findViewById(R.id.button_32)
        button_33 = view.findViewById(R.id.button_33)
    }

    //Init all the grid spaces
    private fun initGrid() {
        grid_11 = GridSpace(button_11)
        grid_12 = GridSpace(button_12)
        grid_13 = GridSpace(button_13)
        grid_21 = GridSpace(button_21)
        grid_22 = GridSpace(button_22)
        grid_23 = GridSpace(button_23)
        grid_31 = GridSpace(button_31)
        grid_32 = GridSpace(button_32)
        grid_33 = GridSpace(button_33)
    }

    //Init the on click listeners for the grid
    private fun initOnClickGrid() {
        button_11.setOnClickListener { checkSpace(grid_11) }
        button_12.setOnClickListener { checkSpace(grid_12) }
        button_13.setOnClickListener { checkSpace(grid_13) }
        button_21.setOnClickListener { checkSpace(grid_21) }
        button_22.setOnClickListener { checkSpace(grid_22) }
        button_23.setOnClickListener { checkSpace(grid_23) }
        button_31.setOnClickListener { checkSpace(grid_31) }
        button_32.setOnClickListener { checkSpace(grid_32) }
        button_33.setOnClickListener { checkSpace(grid_33) }
    }
}