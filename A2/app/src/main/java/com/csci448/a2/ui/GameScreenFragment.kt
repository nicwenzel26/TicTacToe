package com.csci448.a2.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.preference.PreferenceManager
import androidx.preference.SwitchPreferenceCompat
import com.csci448.a2.R
import com.csci448.a2.data.GridSpace
import com.csci448.a2.data.HistoryData

private const val logTag = "448.GSF"
private const val KEY_INDEX_TURN = "player turn"
private const val KEY_INDEX_BOARD = "current board"
private const val KEY_INDEX_WON = "has won"
private const val KEY_INDEX_WINNER = "winner"

class GameScreenFragment:Fragment() {
    /*
   LATEINITS ********************************************************************************
    */
    private lateinit var newGameButton: Button
    private lateinit var playerTurnTextView: TextView
    private lateinit var playerWinTextView: TextView
    private lateinit var returnButton: Button
    private lateinit var historyViewModel: HistoryViewModel

    //There is probably a better way to do this but I couldn't thing of anything but buttons
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

    /*
    VARIABLE INIT ******************************************************************************
     */
    private var listOfGridSpace = mutableListOf<GridSpace>()
    private var numOfPlayers = 2
    private var playerTurn = 0
    private var playerOneSymb = 'x'
    private var playerTwoSymb = 'o'
    private var playerOneResourse = R.drawable.x
    private var playerTwoResourse = R.drawable.o
    private var currentButtons = charArrayOf('b','b','b','b','b','b', 'b', 'b', 'b')
    private var playerWon = false
    private var winningPlayer : String? = null


    //Interface for managing the transition between fragments. Behavior determined by MainActivity
    interface CallBacks {
        fun resetGame()
        fun returnGame()
    }

    //Initially setting callbacks to null
    private var callBacks: CallBacks? = null

    //OnAttach overridden to set callbacks as the current context
    override fun onAttach(context: Context) {
        Log.d(logTag, "onAttach() called")
        callBacks = context as CallBacks?
        super.onAttach(context)
    }

    //OnDetach overridden to reset callbacks back to null
    override fun onDetach() {
        callBacks = null
        super.onDetach()
    }

    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        super.onSaveInstanceState(savedInstanceState)

        savedInstanceState.putInt(KEY_INDEX_TURN, playerTurn )
        savedInstanceState.putCharArray(KEY_INDEX_BOARD, currentButtons)
        savedInstanceState.putBoolean(KEY_INDEX_WON, playerWon)
        savedInstanceState.putString(KEY_INDEX_WINNER, winningPlayer)

    }

    //Setting view models and variables
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Retrieving the shared preferences
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        //Checking to see if user wants two players
        val twoPlayer = sharedPreferences.getBoolean("numPlayers", false)
        //Checking to see if the player wants player 1 to start
        val playerOneFirst = sharedPreferences.getBoolean("humanStart", true)
        //Checking to see if player one wants to be X
        val playerOneX = sharedPreferences.getBoolean("player_symb", true)

        //If the two player preference is checked set the number of players to 2 otherwise to 1
        if(twoPlayer) numOfPlayers = 2
        else numOfPlayers = 1

        //If the player one first preference is checked se the current player turn accordingly
        if(playerOneFirst) playerTurn = 2
        else playerTurn = 1

        currentButtons = savedInstanceState?.getCharArray(KEY_INDEX_BOARD) ?: charArrayOf('b','b','b','b','b','b', 'b', 'b', 'b')

        val currentPlayerTurn = savedInstanceState?.getInt(KEY_INDEX_TURN, 0)?: 0
        if(currentPlayerTurn != 0 ) {
            playerTurn = currentPlayerTurn
            }

        if(playerOneX) {
            playerOneSymb = 'o'
            playerOneResourse = R.drawable.o
            playerTwoSymb = 'x'
            playerTwoResourse = R.drawable.x
        }
        else {
            playerOneSymb = 'x'
            playerOneResourse = R.drawable.x
            playerTwoSymb = 'o'
            playerTwoResourse = R.drawable.o
        }


        val factory = HistoryViewModelFactory(requireContext())
        historyViewModel = ViewModelProvider(this, factory).get(HistoryViewModel::class.java)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //Inflate the game screen XML
        val view = inflater.inflate(R.layout.game_screen_fragment, container, false)

        //GRID SETUP
        initButtons(view)
        initGrid()
        initOnClickGrid()
        addGridToList()

        //Find Buttons and Views *****************************************************
        newGameButton = view.findViewById(R.id.play_again_button)
        playerTurnTextView = view.findViewById(R.id.player_turn)
        playerWinTextView = view.findViewById(R.id.player_wins_text_view)
        returnButton = view.findViewById(R.id.return_button)

        for(i in 0..8) {
            if(currentButtons[i] == playerOneSymb){
                listOfGridSpace[i].button.setImageResource(playerOneResourse)
                listOfGridSpace[i].xOrO = playerOneSymb
            }

            if(currentButtons[i] == playerTwoSymb) {
                listOfGridSpace[i].button.setImageResource(playerTwoResourse)
                listOfGridSpace[i].xOrO = playerTwoSymb
            }
        }

        //Setting current turn text based on preference
        if(playerTurn == 1) playerTurnTextView.text = "Player 1 Turn"
        else {
            playerTurnTextView.text = "Player 2 Turn"
            if(numOfPlayers == 1) {
                computerTurn()
            }
        }

        val hasWon = savedInstanceState?.getBoolean(KEY_INDEX_WON) ?: false
        val winner = savedInstanceState?.getString(KEY_INDEX_WINNER)

        if(hasWon) {
            newGameButton.visibility = View.VISIBLE
            returnButton.visibility = View.VISIBLE
            playerWinTextView.visibility = View.VISIBLE
            playerWinTextView.text = winner
        }

        //Setting on click listeners for end game buttons
        newGameButton.setOnClickListener { callBacks?.resetGame() }
        returnButton.setOnClickListener { callBacks?.returnGame() }

        return view
    }
    //Adding all the grid spaces to the list
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

                gridSpace.button.setImageResource(playerOneResourse)
                gridSpace.xOrO = playerOneSymb
                playerTurn = 2
                currentButtons[gridSpace.position] = playerOneSymb
                playerTurnTextView.text = "Player 2 Turn"

                if(numOfPlayers == 1) {
                    computerTurn()
                }
            }
            else {
                gridSpace.button.setImageResource(playerTwoResourse)
                gridSpace.xOrO = playerTwoSymb
                currentButtons[gridSpace.position] = playerTwoSymb
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
        var gridSpace = GridSpace(badButton, position = -1)

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

        gridSpace.xOrO = playerTwoSymb
        gridSpace.button.setImageResource(playerTwoResourse)
        playerTurn = 1
        currentButtons[gridSpace.position] = playerTwoSymb
        playerTurnTextView.text = "Player 1 Turn"
    }

    private fun createGameHistory(winner: Char) {
        val game = HistoryData()
        if(numOfPlayers == 2) {
            game.singlePlayer = false
            if(winner == playerOneSymb) {
                game.winner = "Player 1"
                game.loser = "Player 2"
                game.didTie = false
            }
            else if (winner == playerTwoSymb){
                game.winner = "Player 2"
                game.loser = "Player 1"
                game.didTie = false
            }

            else {
                game.didTie = true
                game.winner = "Player 1"
                game.loser = "Player 2"
            }
        }

        else {
            game.singlePlayer = true
            if(winner == playerOneSymb) {
                game.winner = "Player 1"
                game.loser = "Computer"
                game.didTie = false
            }

            else if(winner == playerTwoSymb) {
                game.winner = "Computer"
                game.loser = "Player 1"
                game.didTie = false
            }

            else {
                game.didTie = true
                game.winner = "Player 1"
                game.loser = "Computer"
            }
        }

        historyViewModel.addGame(game)

    }

    //Go through all win conditions if won set text and remove the on click listeners
    private fun determineWin(xOrO : Char) {
        if(grid_11.xOrO == xOrO && grid_12.xOrO == xOrO && grid_13.xOrO == xOrO ) {
            setWinText(xOrO)
            createGameHistory(xOrO)
            removeOnClickListener()
        }
        else if(grid_11.xOrO == xOrO && grid_21.xOrO == xOrO && grid_31.xOrO == xOrO) {
            setWinText(xOrO)
            createGameHistory(xOrO)
            removeOnClickListener()}
        else if(grid_11.xOrO == xOrO && grid_22.xOrO == xOrO && grid_33.xOrO == xOrO) {
            setWinText(xOrO)
            createGameHistory(xOrO)
            removeOnClickListener()}
        else if(grid_12.xOrO == xOrO && grid_22.xOrO == xOrO && grid_32.xOrO == xOrO) {
            setWinText(xOrO)
            createGameHistory(xOrO)
            removeOnClickListener()}
        else if(grid_13.xOrO == xOrO && grid_23.xOrO == xOrO && grid_33.xOrO == xOrO) {
            setWinText(xOrO)
            createGameHistory(xOrO)
            removeOnClickListener()}
        else if(grid_13.xOrO == xOrO && grid_22.xOrO == xOrO && grid_31.xOrO == xOrO) {
            setWinText(xOrO)
            createGameHistory(xOrO)
            removeOnClickListener()}
        else if(grid_21.xOrO == xOrO && grid_22.xOrO == xOrO && grid_23.xOrO == xOrO) {
            setWinText(xOrO)
            removeOnClickListener()}
        else if(grid_31.xOrO == xOrO && grid_32.xOrO == xOrO && grid_33.xOrO == xOrO) {
            setWinText(xOrO)
            createGameHistory(xOrO)
            removeOnClickListener()}
    }
    //Removes the on click listeners for all the buttons so after win there is no more play
    private fun removeOnClickListener() {
        for( item in listOfGridSpace) {
            item.button.setOnClickListener {  }
        }
    }

    //Setting the winning player text and making the field visable and buttons visable
    private fun setWinText(xOrO: Char){
        if(xOrO == playerOneSymb) {
            playerWinTextView.text = "Player 1 Wins!!!"
            winningPlayer = "Player 1 Wins!!!"
        }
        else if(numOfPlayers == 2) {
            playerWinTextView.text = "Player 2 Wins!!!"
            winningPlayer = "Player 2 Wins!!!"
        }

        else {
            playerWinTextView.text = "Computer Wins!!!"
            winningPlayer = "Computer Wins!!!"
        }
        playerWinTextView.visibility = View.VISIBLE
        newGameButton.visibility = View.VISIBLE
        returnButton.visibility = View.VISIBLE
        playerWon = true
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
            winningPlayer = "Tie!!!"
            playerWon = true
            playerWinTextView.visibility = View.VISIBLE
            newGameButton.visibility = View.VISIBLE
            returnButton.visibility = View.VISIBLE
            createGameHistory('t')
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
        grid_11 = GridSpace(button_11, position = 0)
        grid_12 = GridSpace(button_12, position =  1)
        grid_13 = GridSpace(button_13, position = 2)
        grid_21 = GridSpace(button_21, position = 3)
        grid_22 = GridSpace(button_22, position = 4)
        grid_23 = GridSpace(button_23, position = 5)
        grid_31 = GridSpace(button_31, position = 6)
        grid_32 = GridSpace(button_32, position = 7)
        grid_33 = GridSpace(button_33, position = 8)
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