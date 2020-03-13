package com.example.minipaint

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class PatternView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    val mRect : RectF = RectF()
    private val PADDING = 4f
    private var mPadding = 0f
    private var mBitmap: Bitmap? = null
    var mCanvas:Canvas?=null

    private var motionTouchEventX = 0f
    private var motionTouchEventY = 0f
    private var currentX = 0f
    private var currentY = 0f

    private var path = Path()

    private val paint = Paint().apply {
        // Smooth out edges of what is drawn without affecting shape.
        isAntiAlias = true
        strokeWidth = resources.getDimension(R.dimen.strokeWidth)
        textSize = resources.getDimension(R.dimen.textSize)
        color=Color.BLACK
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas!!.drawBitmap(
            mBitmap!!, width / 2 - mBitmap!!.width / 2.toFloat(),
            height / 2 - mBitmap!!.height / 2.toFloat(), null
        )

        mCanvas?.drawCircle(mRect.width()/6,mRect.height()/6,10f,paint)
        mCanvas?.drawCircle(mRect.width()/2,mRect.height()/6,10f,paint)
        mCanvas?.drawCircle(mRect.width()-mRect.width()/6,mRect.height()/6,10f,paint)
        mCanvas?.drawCircle(mRect.width()/6,mRect.height()/2,10f,paint)
        mCanvas?.drawCircle(mRect.width()/2,mRect.height()/2,10f,paint)
        mCanvas?.drawCircle(mRect.width()-mRect.width()/6,mRect.height()/2,10f,paint)
        mCanvas?.drawCircle(mRect.width()/6,mRect.height()-mRect.height()/6,10f,paint)
        mCanvas?.drawCircle(mRect.width()/2,mRect.height()-mRect.height()/6,10f,paint)
        mCanvas?.drawCircle(mRect.width()-mRect.width()/6,mRect.height()-mRect.height()/6,10f,paint)

    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        val bitmapWidth = w - 2 * mPadding
        val bitmapHeight = h - 2 * mPadding
        mRect.set(0f,0f,bitmapWidth,bitmapHeight)

        mBitmap = Bitmap.createBitmap(
            mRect.width().toInt(),
            mRect.height().toInt(),
            Bitmap.Config.ARGB_8888
        )
        mCanvas = Canvas(mBitmap!!)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        motionTouchEventX = event!!.x
        motionTouchEventY = event.y

        when(event.action){
            MotionEvent.ACTION_DOWN -> {
                path.reset()
                path.moveTo(mRect.width()/6, mRect.height()/6)
                currentX = motionTouchEventX
                currentY = motionTouchEventY
            }

            MotionEvent.ACTION_MOVE -> {
                path.lineTo(motionTouchEventX,motionTouchEventY)
                mCanvas?.drawPath(path, paint)
                invalidate()

            }

            MotionEvent.ACTION_UP -> {
                path.reset()
            }
        }
        return true

    }
}