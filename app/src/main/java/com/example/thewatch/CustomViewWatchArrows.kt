package com.example.thewatch

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.AttributeSet
import android.view.View
import androidx.core.content.withStyledAttributes
import androidx.core.graphics.withSave
import java.util.*

class CustomViewWatchArrows @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var color1 = 0
    private var color2 = 0
    private var color3 = 0

    private var mWidth = 0.0f
    private var mHeight = 0.0f

    private var hours = 0.0f
    private var minutes = 0.0f
    private var seconds = 0.0f
    private var radius = 0.0f

    private val mHandler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)

            getTime()
            invalidate()
        }
    }

    init {
        context.withStyledAttributes(attrs, R.styleable.CustomViewWatchArrows) {
            color1 = getColor(R.styleable.CustomViewWatchArrows_color1, 0)
            color2 = getColor(R.styleable.CustomViewWatchArrows_color2, 0)
            color3 = getColor(R.styleable.CustomViewWatchArrows_color3, 0)
        }

        getTime()

        mHandler.sendEmptyMessageDelayed(1, 1000)
    }

    private val secArrow = Paint().apply {
        isAntiAlias = true
        color = color1
        style = Paint.Style.STROKE
        strokeWidth = 4f
    }

    private val minArrow = Paint().apply {
        isAntiAlias = true
        color = color2
        style = Paint.Style.STROKE
        strokeWidth = 12f
    }

    private val hourArrow = Paint().apply {
        isAntiAlias = true
        color = color3
        style = Paint.Style.STROKE
        strokeWidth = 16f
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        mWidth = width.toFloat()
        mHeight = height.toFloat()
        radius = mWidth/2 - mWidth/20

        canvas?.withSave {
            canvas.rotate(360/60 * seconds, mWidth /2, mHeight /2)
            canvas.drawLine(mWidth /2, mHeight /2, mWidth /2, mHeight /2 - radius + mHeight/40, secArrow)
        }

        canvas?.withSave {
            canvas.rotate(360/60 * minutes + seconds * 0.1f, mWidth /2, mHeight /2)
            canvas.drawLine(mWidth /2, mHeight /2, mWidth /2, mHeight /2 - radius + mHeight/20, minArrow)
        }

        canvas?.withSave {
            canvas.rotate(360/12 * hours + minutes * 0.5f, mWidth /2, mHeight /2)
            canvas.drawLine(mWidth /2, mHeight /2, mWidth /2, mHeight /2 - radius + mHeight/10, hourArrow)
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


