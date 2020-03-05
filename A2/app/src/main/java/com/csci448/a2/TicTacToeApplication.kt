package com.csci448.a2

import android.app.Application
import android.util.Log

private const val logTag = "448.APP"

class TicTacToeApplication:Application() {
    override fun onCreate() {
        Log.d(logTag, "onCreate() called")
        super.onCreate()
    }
}