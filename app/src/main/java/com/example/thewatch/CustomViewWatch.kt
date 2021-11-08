package com.example.thewatch

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import java.util.*

class CustomViewWatch @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr){

     var hours: Int? = null
     var minutes: Int? = null
     var seconds: Int? = null

    private val blue = Paint().apply {
        isAntiAlias = true
        color = Color.BLUE
        style = Paint.Style.FILL
        strokeWidth = 10f
    }

    private val path = Path().apply {
        moveTo(10f, 10f)
        lineTo(300f, 10f)
        lineTo(300f, 300f)
        close()
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
//        canvas?.drawRGB(200, 100, 100)
//        canvas?.drawColor(Color.RED)

//        canvas?.drawCircle(width/2f, height/2f, 100f, blue)
//
//        canvas?.drawPath(path, blue)

//        canvas?.translate(300f, 700f)
//        canvas?.rotate(45f)
//        canvas?.drawPath(path, blue)

        canvas?.drawLine(width/2f, height/2f, width/2f, height/2f + 100f, blue)
    }


    private fun getTime() {
        val calendar = Calendar.getInstance()
        hours = calendar.get(Calendar.HOUR)
        minutes = calendar.get(Calendar.MINUTE)
        seconds = calendar.get(Calendar.SECOND)
    }


}


