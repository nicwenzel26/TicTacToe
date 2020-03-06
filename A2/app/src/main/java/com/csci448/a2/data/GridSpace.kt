package com.csci448.a2.data

import android.widget.ImageButton
import java.text.FieldPosition

data class GridSpace(val button: ImageButton, var xOrO :Char? = null, val position: Int) {
}