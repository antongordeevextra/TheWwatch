package com.example.thewatch

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.core.graphics.withSave
import java.util.*
import kotlin.math.min

class CustomViewWatch @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var mWidth: Float? = null
    private var mHeight: Float? = null

    private var hours: Float? = null
    private var minutes: Float? = null
    private var seconds: Float? = null

    private val mHandler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when(msg.what) {
                1 -> {
                    getTime()
                    invalidate()
                }
                else -> Unit
            }
        }
    }

    init {
        getTime()
        Log.d("proverka", "init")

        mHandler.sendEmptyMessageDelayed(1, 1000)
    }

    private val blue = Paint().apply {
        isAntiAlias = true
        color = Color.BLUE
        style = Paint.Style.STROKE
        strokeWidth = 4f
    }

    private val red = Paint().apply {
        isAntiAlias = true
        color = Color.RED
        style = Paint.Style.STROKE
        strokeWidth = 12f
    }

    private val yellow = Paint().apply {
        isAntiAlias = true
        color = Color.BLACK
        style = Paint.Style.STROKE
        strokeWidth = 16f
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        mHandler.sendEmptyMessageDelayed(1, 1000)

        mWidth = width.toFloat()
        mHeight = height.toFloat()

        canvas?.withSave {
            canvas.rotate(360/60 * seconds!!, mWidth!! /2, mHeight!! /2)
            canvas.drawLine(mWidth!! /2, mHeight!! /2, mWidth!! /2, mHeight!! /4, blue)
        }

        canvas?.withSave {
            canvas.rotate(360/60 * minutes!! + seconds!! * 0.1f, mWidth!! /2, mHeight!! /2)
            canvas.drawLine(mWidth!! /2, mHeight!! /2, mWidth!! /2, mHeight!! /3.5f, red)
        }

        canvas?.withSave {
            canvas.rotate(360/12 * hours!! + minutes!! * 0.5f, mWidth!! /2, mHeight!! /2)
            canvas.drawLine(mWidth!! /2, mHeight!! /2, mWidth!! /2, mHeight!! /3, yellow)
        }
    }


    private fun getTime() {
        val calendar = Calendar.getInstance(TimeZone.getTimeZone("Europe/Moscow"))
        hours = calendar.get(Calendar.HOUR).toFloat()
        minutes = calendar.get(Calendar.MINUTE).toFloat()
        seconds = calendar.get(Calendar.SECOND).toFloat()
    }


}


