package com.example.minipaint

import android.content.Context
import android.graphics.*
import android.view.MotionEvent
import android.view.View
import android.view.ViewConfiguration
import androidx.core.content.res.ResourcesCompat

private const val STROKE_WIDTH = 12f // has to be float

class MyCanvasView(context:Context):View(context) {
    private lateinit var extraCanvas: Canvas
    private lateinit var extraBitmap: Bitmap
    private var motionTouchEventX = 0f
    private var motionTouchEventY = 0f
    private var currentX = 0f
    private var currentY = 0f
    private lateinit var frame: Rect
    private lateinit var eraseFrame: Rect
    private var doubleTapDetected=false

    private var eraseTopLeftX=0f
    private var eraseTopLeftY=0f
    private var eraseTopRightX=0f
    private var eraseTopRightY=0f
    private var eraseBottomLeftX=0f
    private var eraseBottomLeftY=0f
    private var eraseBottomRightX=0f
    private var eraseBottomRightY=0f

    private val backgroundColor = ResourcesCompat.getColor(resources, R.color.colorBackground, null)
    private val drawColor = ResourcesCompat.getColor(resources, R.color.colorPaint, null)
    private val touchTolerance = ViewConfiguration.get(context).scaledTouchSlop

    // Set up the paint with which to draw.
    private val paint = Paint().apply {
        color = drawColor
        // Smooths out edges of what is drawn without affecting shape.
        isAntiAlias = true
        // Dithering affects how colors with higher-precision than the device are down-sampled.
        isDither = true
        style = Paint.Style.STROKE // default: FILL
        strokeJoin = Paint.Join.ROUND // default: MITER
        strokeCap = Paint.Cap.ROUND // default: BUTT
        strokeWidth = STROKE_WIDTH // default: Hairline-width (really thin)
    }

    private val erasePaint = Paint().apply {
        color=backgroundColor
    }

    private var path = Path()

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        if (::extraBitmap.isInitialized) extraBitmap.recycle()

        extraBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        extraCanvas = Canvas(extraBitmap)
        extraCanvas.drawColor(backgroundColor)

        // Calculate a rectangular frame around the picture.
        val inset = 40
        frame = Rect(inset, inset, width - inset, height - inset)
        eraseFrame = Rect(0,0,0,0)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawBitmap(extraBitmap,0f,0f,null)
        canvas?.drawRect(frame, paint)
        canvas?.drawRect(eraseFrame,erasePaint)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        motionTouchEventX = event!!.x
        motionTouchEventY = event.y

        when (event.action) {
            MotionEvent.ACTION_DOWN -> touchStart()
            MotionEvent.ACTION_POINTER_DOWN -> doubleTouchStart()
            MotionEvent.ACTION_MOVE -> touchMove()
            MotionEvent.ACTION_POINTER_UP -> doubleTouchEnd()
            MotionEvent.ACTION_UP -> touchUp()
        }
        return true
    }

    private fun touchStart() {
        path.reset()
        path.moveTo(motionTouchEventX, motionTouchEventY)
        currentX = motionTouchEventX
        currentY = motionTouchEventY
    }

    private fun touchMove() {
        if(!doubleTapDetected) {
            eraseFrame = Rect(0,0,0,0)
            val dx = Math.abs(motionTouchEventX - currentX)
            val dy = Math.abs(motionTouchEventY - currentY)
            if (dx >= touchTolerance || dy >= touchTolerance) {
                // QuadTo() adds a quadratic bezier from the last point,
                // approaching control point (x1,y1), and ending at (x2,y2).
                path.quadTo(
                    currentX,
                    currentY,
                    (motionTouchEventX + currentX) / 2,
                    (motionTouchEventY + currentY) / 2
                )
                currentX = motionTouchEventX
                currentY = motionTouchEventY
                // Draw the path in the extra bitmap to cache it.
                extraCanvas.drawPath(path, paint)
            }
            invalidate()
        }
    }

    private fun touchUp() {
        doubleTapDetected=false
        // Reset the path so it doesn't get drawn again.
        path.reset()
    }

    private fun doubleTouchStart() {
        doubleTapDetected=true
        eraseTopLeftX=currentX
        eraseTopLeftY=currentY
        eraseTopRightX=motionTouchEventX
        eraseTopRightY=motionTouchEventY

    }

    private fun doubleTouchEnd() {
        doubleTapDetected=false
        eraseBottomRightX=motionTouchEventX
        eraseBottomRightY=motionTouchEventY
//        eraseFrame = Rect(eraseTopLeftX.toInt(),eraseTopLeftY.toInt(),eraseBottomRightX.toInt(),
//            eraseBottomRightY.toInt())
        eraseFrame = Rect(50,50,width-50,height-50)
        extraBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        extraCanvas = Canvas(extraBitmap)
        extraCanvas.drawColor(backgroundColor)
        invalidate()
    }
}