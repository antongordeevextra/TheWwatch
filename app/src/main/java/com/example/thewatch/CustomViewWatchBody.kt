package com.example.thewatch

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View
import androidx.core.graphics.withSave

class CustomViewWatchBody @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var mWidth = 0.0f
    private var mHeight = 0.0f
    private var radius = 0.0f

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        mWidth = width.toFloat()
        mHeight = height.toFloat()
        radius = mWidth/2 - 50

        canvas?.drawCircle(mWidth/2,mHeight/2,radius, black)

        canvas?.drawCircle(mWidth/2,mHeight/2,20f, center)

        for (i in 1..12) {
            canvas?.withSave {
                canvas.rotate(360/12f * (i), mWidth/2, mHeight/2)
                canvas.drawLine(mWidth/2, radius, mWidth/2, mHeight/2 - radius, black)
                canvas.drawText((i).toString(), mWidth/2 - 25, mHeight/2-mWidth/2+200, text)
            }
        }

        for (i in 1..60) {
            canvas?.withSave {
                canvas.rotate(360/60f * (i), mWidth/2, mHeight/2)
                canvas.drawLine(mWidth/2, radius-20, mWidth/2, mHeight/2 - radius, minuteLine)
            }
        }
    }
}