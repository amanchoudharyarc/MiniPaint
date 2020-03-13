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

    private var one=true
    private var two=true
    private var three=true
    private var four=true
    private var five=true
    private var six=true
    private var seven=true
    private var eight=true
    private var nine=true

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
//                path.reset()
//                path.moveTo(mRect.width()/6, mRect.height()/6)
//                currentX = motionTouchEventX
//                currentY = motionTouchEventY
            }

            MotionEvent.ACTION_MOVE -> {
//                path.lineTo(motionTouchEventX,motionTouchEventY)
//                mCanvas?.drawPath(path, paint)
                if ((mRect.width()/12)*1<motionTouchEventX&&motionTouchEventX<(mRect.width()/12)*3
                    && (mRect.height()/12)*1<motionTouchEventY&&motionTouchEventY<(mRect.height()/12)*3
                    && one){
                    one=false
                    if(currentX==0f || currentY==0f){
                        currentX = mRect.width()/6
                        currentY = mRect.height()/6
                    }
                    mCanvas?.drawLine(currentX,currentY,mRect.width()/6,mRect.height()/6,paint)
                    invalidate()
                    currentX = mRect.width()/6
                    currentY = mRect.height()/6
                }
                else if ((mRect.width()/12)*5<motionTouchEventX&&motionTouchEventX<(mRect.width()/12)*7
                    && (mRect.height()/12)*1<motionTouchEventY&&motionTouchEventY<(mRect.height()/12)*3
                    && two){
                    two=false
                    if(currentX==0f || currentY==0f){
                        currentX = mRect.width()/2
                        currentY = mRect.height()/6
                    }
                    mCanvas?.drawLine(currentX,currentY,mRect.width()/2,mRect.height()/6,paint)
                    invalidate()
                    currentX = mRect.width()/2
                    currentY = mRect.height()/6
                }
                else if ((mRect.width()/12)*9<motionTouchEventX&&motionTouchEventX<(mRect.width()/12)*11
                    && (mRect.height()/12)*1<motionTouchEventY&&motionTouchEventY<(mRect.height()/12)*3
                    && three){
                    three=false
                    if(currentX==0f || currentY==0f){
                        currentX = mRect.width()-mRect.width()/6
                        currentY = mRect.height()/6
                    }
                    mCanvas?.drawLine(currentX,currentY,mRect.width()-mRect.width()/6,mRect.height()/6,paint)
                    invalidate()
                    currentX = mRect.width()-mRect.width()/6
                    currentY = mRect.height()/6
                }
                else if ((mRect.width()/12)*1<motionTouchEventX&&motionTouchEventX<(mRect.width()/12)*3
                    && (mRect.height()/12)*5<motionTouchEventY&&motionTouchEventY<(mRect.height()/12)*7
                    && four){
                    four=false
                    if(currentX==0f || currentY==0f){
                        currentX = mRect.width()/6
                        currentY = mRect.height()/2
                    }
                    mCanvas?.drawLine(currentX,currentY,mRect.width()/6,mRect.height()/2,paint)
                    invalidate()
                    currentX = mRect.width()/6
                    currentY = mRect.height()/2
                }
                else if ((mRect.width()/12)*5<motionTouchEventX&&motionTouchEventX<(mRect.width()/12)*7
                    && (mRect.height()/12)*5<motionTouchEventY&&motionTouchEventY<(mRect.height()/12)*7
                    && five){
                    five=false
                    if(currentX==0f || currentY==0f){
                        currentX = mRect.width()/2
                        currentY = mRect.height()/2
                    }
                    mCanvas?.drawLine(currentX,currentY,mRect.width()/2,mRect.height()/2,paint)
                    invalidate()
                    currentX = mRect.width()/2
                    currentY = mRect.height()/2
                }
                else if ((mRect.width()/12)*9<motionTouchEventX&&motionTouchEventX<(mRect.width()/12)*11
                    && (mRect.height()/12)*5<motionTouchEventY&&motionTouchEventY<(mRect.height()/12)*7
                    && six){
                    six=false
                    if(currentX==0f || currentY==0f){
                        currentX = mRect.width()-mRect.width()/6
                        currentY = mRect.height()/2
                    }
                    mCanvas?.drawLine(currentX,currentY,mRect.width()-mRect.width()/6,mRect.height()/2,paint)
                    invalidate()
                    currentX = mRect.width()-mRect.width()/6
                    currentY = mRect.height()/2
                }
                else if ((mRect.width()/12)*1<motionTouchEventX&&motionTouchEventX<(mRect.width()/12)*3
                    && (mRect.height()/12)*9<motionTouchEventY&&motionTouchEventY<(mRect.height()/12)*11
                    && seven){
                    seven=false
                    if(currentX==0f || currentY==0f){
                        currentX = mRect.width()/6
                        currentY = mRect.height()-mRect.height()/6
                    }
                    mCanvas?.drawLine(currentX,currentY,mRect.width()/6,mRect.height()-mRect.height()/6,paint)
                    invalidate()
                    currentX = mRect.width()/6
                    currentY = mRect.height()-mRect.height()/6
                }
                else if ((mRect.width()/12)*5<motionTouchEventX&&motionTouchEventX<(mRect.width()/12)*7
                    && (mRect.height()/12)*9<motionTouchEventY&&motionTouchEventY<(mRect.height()/12)*11
                    && eight){
                    eight=false
                    if(currentX==0f || currentY==0f){
                        currentX = mRect.width()/2
                        currentY = mRect.height()-mRect.height()/6
                    }
                    mCanvas?.drawLine(currentX,currentY,mRect.width()/2,mRect.height()-mRect.height()/6,paint)
                    invalidate()
                    currentX = mRect.width()/2
                    currentY = mRect.height()-mRect.height()/6
                }
                else if ((mRect.width()/12)*9<motionTouchEventX&&motionTouchEventX<(mRect.width()/12)*11
                    && (mRect.height()/12)*9<motionTouchEventY&&motionTouchEventY<(mRect.height()/12)*11
                    && nine){
                    nine=false
                    if(currentX==0f || currentY==0f){
                        currentX = mRect.width()-mRect.width()/6
                        currentY = mRect.height()-mRect.height()/6
                    }
                    mCanvas?.drawLine(currentX,currentY,mRect.width()-mRect.width()/6,mRect.height()-mRect.height()/6,paint)
                    invalidate()
                    currentX = mRect.width()-mRect.width()/6
                    currentY = mRect.height()-mRect.height()/6
                }


            }

            MotionEvent.ACTION_UP -> {
//                path.reset()
//                mCanvas?.drawLine(currentX,currentY,motionTouchEventX,motionTouchEventY,paint)
//                invalidate()
            }
        }
        return true

    }
}