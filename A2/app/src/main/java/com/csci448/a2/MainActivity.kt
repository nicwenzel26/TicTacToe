package com.csci448.a2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.csci448.a2.ui.GameScreenFragment
import com.csci448.a2.ui.History.HistoryFragment
import com.csci448.a2.ui.HomeScreenFragment
import com.csci448.a2.ui.PrefScreenFragment

class MainActivity : AppCompatActivity(), HomeScreenFragment.CallBacks, GameScreenFragment.CallBacks, HistoryFragment.CallBacks {

    override fun onNewGameSelect() {
        val fragment = GameScreenFragment()
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).addToBackStack(null).commit()
    }

    override fun onPrefSelect() {
        val fragment = PrefScreenFragment()
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container,fragment).addToBackStack(null).commit()
    }

    override fun onExitSelect() {
        System.exit(0)
    }

    override fun onHistorySelect() {
        val fragment = HistoryFragment()
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).addToBackStack(null).commit()
    }

    override fun returnGame() {
        val fragment = HomeScreenFragment()
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container,fragment).commit()
    }

    override fun resetGame() {
        val fragment = GameScreenFragment()
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val currentFragment = supportFragmentManager.findFragmentById(R.id.fragment_container)

        if(currentFragment == null) {
            val fragment = HomeScreenFragment()
            supportFragmentManager.beginTransaction().add(R.id.fragment_container, fragment).commit()
        }
    }
}
