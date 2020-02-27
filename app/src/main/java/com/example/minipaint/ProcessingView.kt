package com.example.minipaint

import android.content.Context
import android.graphics.*
import android.os.Build
import android.os.Handler
import android.util.AttributeSet
import android.util.Log
import android.view.View

class ProcessingView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    val mRect :RectF=RectF()
    private val PADDING = 4f
    private var mPadding = 0f
    private var mBitmap: Bitmap? = null
    var startAngle=0f
    var handler2=Handler()
    var runnable:Runnable?=null
    var mRadius=0f
    var mCanvas:Canvas?=null
    var path=Path()
    var path2=Path()
    val SIN_45=0.7071f
    val lightBlueColor=Color.parseColor("#90caf9")
    val darkBlueColor=Color.parseColor("#2196f3")

    init {
        val r = context.resources
        val scale = r.displayMetrics.density
        mPadding=scale*PADDING
        handler2= Handler()
        runnable= Runnable {
            startAngle=startAngle%360+45f
            invalidate()
            Log.d("checkd",""+startAngle)
            doff()
        }
    }

    private fun doff(){
        handler2.postDelayed(runnable!!,150)
    }

    private val paint = Paint().apply {
        // Smooth out edges of what is drawn without affecting shape.
        isAntiAlias = true
        strokeWidth = resources.getDimension(R.dimen.strokeWidth)
        textSize = resources.getDimension(R.dimen.textSize)
        color=lightBlueColor
    }

    private val paint2 = Paint().apply {
        isAntiAlias = true
        strokeWidth = resources.getDimension(R.dimen.strokeWidth)
        textSize = resources.getDimension(R.dimen.textSize)
        color=darkBlueColor
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas!!.drawBitmap(
            mBitmap!!, width / 2 - mBitmap!!.width / 2.toFloat(),
            height / 2 - mBitmap!!.height / 2.toFloat(), null
        )
//        mRect.set(300f,300f,700f,700f)
//        canvas?.drawCircle(500f,500f,200f,paint)
//        canvas?.drawArc(mRect,-90f,45f,true,paint2)
        path.addRect(mRect.width()/2-5f,0f,mRect.width()/2+5f,mRect.height(),Path.Direction.CCW)
        path.addRect(0f,mRect.height()/2-5f,mRect.width(),mRect.height()/2+5f,Path.Direction.CCW)
//        path.addRect((mRadius+mRadius*SIN_45)-5f,((1-SIN_45)*mRadius),(mRadius-mRadius*SIN_45)+5f,
//            (2*mRadius-(1-SIN_45)*mRadius),Path.Direction.CW)
        path.addCircle(mRect.width()/2,mRect.height()/2,mRadius/2,Path.Direction.CCW)
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            mCanvas?.clipPath(path, Region.Op.DIFFERENCE)
        } else {
            mCanvas?.clipOutPath(path)
        }
        mCanvas?.drawCircle(mRect.width()/2,mRect.height()/2,mRadius,paint)
        mCanvas?.drawArc(mRect,startAngle,45f,true,paint2)

    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        val bitmapWidth = w - 2 * mPadding
        val bitmapHeight = h - 2 * mPadding
        mRadius = Math.min(bitmapWidth / 2, bitmapHeight / 2)
        mRect.set(0f,0f,bitmapWidth,bitmapHeight)

        mBitmap = Bitmap.createBitmap(
            mRect.width().toInt(),
            mRect.height().toInt(),
            Bitmap.Config.ARGB_8888
        )
        mCanvas = Canvas(mBitmap!!)
        doff()
        runnable= Runnable {
            startAngle=startAngle%360+45f
            invalidate()
            Log.d("checkd",""+startAngle)
            doff()
        }
//        mCanvas?.drawCircle(mRect.width()/2,mRect.height()/2,mRadius,paint)
//        mCanvas?.drawArc(mRect,startAngle,45f,true,paint2)
    }
}