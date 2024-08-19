package com.yuanyuansapplication.app.customview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import com.yuanyuansapplication.app.R

class ArcProgressView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var progress = 0f
    private val paint = Paint()
    private val rect = RectF()

    init {
        paint.isAntiAlias = true
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 20f
        paint.color = ContextCompat.getColor(context, R.color.gray_100) // Change to desired color
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val width = width.toFloat()
        val height = height.toFloat()
        val radius = width / 2
        val centerX = width / 2
        val centerY = height / 2

        rect.set(
            centerX - radius + 20,
            centerY - radius + 20,
            centerX + radius - 20,
            centerY + radius - 20
        )

        canvas.drawArc(rect, 135f, 270f * progress / 100, false, paint)
    }

    fun setProgress(progress: Float) {
        this.progress = progress
        invalidate()
    }
}
