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

class CustomViewWatchArrows @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var mWidth = 0.0f
    private var mHeight = 0.0f

    private var hours = 0.0f
    private var minutes = 0.0f
    private var seconds = 0.0f

    private val mHandler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)

            getTime()
            invalidate()
        }
    }

    init {
        getTime()

        mHandler.sendEmptyMessageDelayed(1, 1000)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        mWidth = width.toFloat()
        mHeight = height.toFloat()

        canvas?.withSave {
            canvas.rotate(360/60 * seconds!!, mWidth /2, mHeight /2)
            canvas.drawLine(mWidth /2, mHeight /2, mWidth /2, mHeight /4, blue)
        }

        canvas?.withSave {
            canvas.rotate(360/60 * minutes!! + seconds!! * 0.1f, mWidth /2, mHeight /2)
            canvas.drawLine(mWidth /2, mHeight /2, mWidth /2, mHeight /3.5f, red)
        }

        canvas?.withSave {
            canvas.rotate(360/12 * hours!! + minutes!! * 0.5f, mWidth /2, mHeight /2)
            canvas.drawLine(mWidth /2, mHeight /2, mWidth /2, mHeight /3, black)
        }

        mHandler.sendEmptyMessageDelayed(1, 1000)
    }

    private fun getTime() {
        val calendar = Calendar.getInstance(TimeZone.getTimeZone("Europe/Moscow"))
        hours = calendar.get(Calendar.HOUR).toFloat()
        minutes = calendar.get(Calendar.MINUTE).toFloat()
        seconds = calendar.get(Calendar.SECOND).toFloat()
    }
}


