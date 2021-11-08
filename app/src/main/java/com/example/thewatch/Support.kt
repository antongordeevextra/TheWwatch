package com.example.thewatch

import android.graphics.Color
import android.graphics.Paint

val black = Paint().apply {
    isAntiAlias = true
    color = Color.BLACK
    style = Paint.Style.STROKE
    strokeWidth = 16f
}

val center = Paint().apply {
    isAntiAlias = true
    color = Color.BLACK
    style = Paint.Style.FILL_AND_STROKE
    strokeWidth = 1f
}

val minuteLine = Paint().apply {
    isAntiAlias = true
    color = Color.BLACK
    style = Paint.Style.STROKE
    strokeWidth = 2f
}

val text = Paint().apply {
    isAntiAlias = true
    textSize = 50f
    color = Color.BLACK
    style = Paint.Style.STROKE
    strokeWidth = 3f
}