package com.csci448.a2

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import java.util.*

class GameScreenFragment:Fragment() {

    private lateinit var newGameButton: Button
    private lateinit var playerTurnTextView: TextView
    private lateinit var playerWinTextView: TextView
    private lateinit var returnButton: Button

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

    private var listOfGridSpace = mutableListOf<GridSpace>()

    private var numOfPlayers = 2
    private var playerTurn = 1

    interface CallBacks {
        fun resetGame()
        fun returnGame()
    }

    private var callBacks: CallBacks? = null

    override fun onAttach(context: Context) {
        callBacks = context as CallBacks?
        super.onAttach(context)
    }

    override fun onDetach() {
        callBacks = null
        super.onDetach()
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.game_screen_fragment, container, false)

        //GRID SETUP
        initButtons(view)
        initGrid()
        initOnClickGrid()
        addGridToList()


        newGameButton = view.findViewById(R.id.play_again_button)
        playerTurnTextView = view.findViewById(R.id.player_turn)
        playerWinTextView = view.findViewById(R.id.player_wins_text_view)
        returnButton = view.findViewById(R.id.return_button)

        playerTurnTextView.text = "Player 1 Turn"

        newGameButton.setOnClickListener { callBacks?.resetGame() }
        returnButton.setOnClickListener { callBacks?.returnGame() }

        return view
    }

    private fun addGridToList() {
        listOfGridSpace.add(grid_11)
        listOfGridSpace.add(grid_12)
        listOfGridSpace.add(grid_13)
        listOfGridSpace.add(grid_21)
        listOfGridSpace.add(grid_22)
        listOfGridSpace.add(grid_23)
        listOfGridSpace.add(grid_31)
        listOfGridSpace.add(grid_32)
        listOfGridSpace.add(grid_33)

    }

    //Check if space is used. If yes tell the user, if no assign their piece
    private fun checkSpace(gridSpace: GridSpace) {
        if(gridSpace.xOrO == null) {
            if(playerTurn == 1) {
                gridSpace.button.setImageResource(R.drawable.x)
                gridSpace.xOrO = 'x'
                playerTurn = 2
                playerTurnTextView.text = "Player 2 Turn"

                if(numOfPlayers == 1) {
                    computerTurn()
                }
            }
            else {
                gridSpace.button.setImageResource(R.drawable.o)
                gridSpace.xOrO = 'o'
                playerTurn = 1
                playerTurnTextView.text = "Player 1 Turn"
            }
            checkWIn()
        }
        else {
            Toast.makeText(context, "That space is already taken", Toast.LENGTH_SHORT).show()
        }
    }
    //Turn for the computer if the player is playing a 1 person game
    private fun computerTurn() {
        var randGridSpace = (1..9).random()
        val badButton = ImageButton(context)
        var gridSpace = GridSpace(badButton)

        when(randGridSpace) {
            1 -> gridSpace = grid_11
            2 -> gridSpace = grid_12
            3 -> gridSpace = grid_13
            4 -> gridSpace = grid_21
            5 -> gridSpace = grid_22
            6 -> gridSpace = grid_23
            7 -> gridSpace = grid_31
            8 -> gridSpace = grid_32
            9 -> gridSpace = grid_33
            else -> gridSpace = gridSpace
        }

        while(gridSpace.xOrO != null) {
            randGridSpace = (1..9).random()
            when(randGridSpace) {
                1 -> gridSpace = grid_11
                2 -> gridSpace = grid_12
                3 -> gridSpace = grid_13
                4 -> gridSpace = grid_21
                5 -> gridSpace = grid_22
                6 -> gridSpace = grid_23
                7 -> gridSpace = grid_31
                8 -> gridSpace = grid_32
                9 -> gridSpace = grid_33
                else -> gridSpace = gridSpace
            }
        }

        gridSpace.xOrO = 'o'
        gridSpace.button.setImageResource(R.drawable.o)
        playerTurn = 1
        playerTurnTextView.text = "Player 1 Turn"
    }
    //Go through all win conditions if won set text and remove the on click listeners
    private fun determineWin(xOrO : Char) {
        if(grid_11.xOrO == xOrO && grid_12.xOrO == xOrO && grid_13.xOrO == xOrO ) {
            setWinText(xOrO)
            removeOnClickListener()
        }
        else if(grid_11.xOrO == xOrO && grid_21.xOrO == xOrO && grid_31.xOrO == xOrO) {
            setWinText(xOrO)
            removeOnClickListener()}
        else if(grid_11.xOrO == xOrO && grid_22.xOrO == xOrO && grid_33.xOrO == xOrO) {
            setWinText(xOrO)
            removeOnClickListener()}
        else if(grid_12.xOrO == xOrO && grid_22.xOrO == xOrO && grid_32.xOrO == xOrO) {
            setWinText(xOrO)
            removeOnClickListener()}
        else if(grid_13.xOrO == xOrO && grid_23.xOrO == xOrO && grid_33.xOrO == xOrO) {
            setWinText(xOrO)
            removeOnClickListener()}
        else if(grid_13.xOrO == xOrO && grid_22.xOrO == xOrO && grid_31.xOrO == xOrO) {
            setWinText(xOrO)
            removeOnClickListener()}
        else if(grid_21.xOrO == xOrO && grid_22.xOrO == xOrO && grid_23.xOrO == xOrO) {
            setWinText(xOrO)
            removeOnClickListener()}
        else if(grid_31.xOrO == xOrO && grid_32.xOrO == xOrO && grid_33.xOrO == xOrO) {
            setWinText(xOrO)
            removeOnClickListener()}
    }
    //Removes the on click listeners for all the buttons so after win there is no more play
    private fun removeOnClickListener() {
        grid_11.button.setOnClickListener {  }
        grid_12.button.setOnClickListener {  }
        grid_13.button.setOnClickListener {  }
        grid_21.button.setOnClickListener { }
        grid_22.button.setOnClickListener {  }
        grid_23.button.setOnClickListener { }
        grid_31.button.setOnClickListener {  }
        grid_32.button.setOnClickListener {  }
        grid_33.button.setOnClickListener {  }
    }

    //Setting the winning player text and making the field visable and buttons visable
    private fun setWinText(xOrO: Char){
        if(xOrO == 'x') {
            playerWinTextView.text = "Player 1 Wins!!!"
        }
        else {
            playerWinTextView.text = "Player 2 Wins!!!"
        }
        playerWinTextView.visibility = View.VISIBLE
        newGameButton.visibility = View.VISIBLE
        returnButton.visibility = View.VISIBLE
    }

    //Go through the win conditions for each player
    private fun checkWIn() {
        determineWin('x')
        determineWin('o')
        determineTie()
    }

    private fun determineTie() {
        var allFull = true
        for(item in listOfGridSpace) {
            if(item.xOrO == null) {
                allFull = false
            }
        }

        if(allFull) {
            playerWinTextView.text = "Tie!!!"
            playerWinTextView.visibility = View.VISIBLE
            newGameButton.visibility = View.VISIBLE
            returnButton.visibility = View.VISIBLE
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