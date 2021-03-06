package com.example.minipaint

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View

class PatternView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val mRect : RectF = RectF()
//    private val PADDING = 4f
    private var mPadding = 0f
    private var mBitmap: Bitmap? = null
    private var mCanvas:Canvas?=null

    private var motionTouchEventX = 0f
    private var motionTouchEventY = 0f
    private var currentX = 0f
    private var currentY = 0f

    private var mPath = Path()

    private var one=true
    private var two=true
    private var three=true
    private var four=true
    private var five=true
    private var six=true
    private var seven=true
    private var eight=true
    private var nine=true

    private var last=0
    private var output=StringBuilder()
    private var realoutput=String()
    private var drawLinePoints = FloatArray(4)

    private val paint = Paint().apply {
        // Smooth out edges of what is drawn without affecting shape.
        isAntiAlias = true
        strokeWidth = resources.getDimension(R.dimen.strokeWidth)
        textSize = resources.getDimension(R.dimen.textSize)
        style=Paint.Style.FILL_AND_STROKE
        strokeJoin=Paint.Join.ROUND
        strokeCap=Paint.Cap.ROUND
        color=Color.BLACK
//        isDither=true
    }

    private val paintWhite = Paint().apply {
        // Smooth out edges of what is drawn without affecting shape.
        isAntiAlias = true
        strokeWidth = resources.getDimension(R.dimen.strokeWidthWhite)
        textSize = resources.getDimension(R.dimen.textSize)
        style=Paint.Style.FILL_AND_STROKE
        strokeJoin=Paint.Join.ROUND
        strokeCap=Paint.Cap.ROUND
        color=Color.WHITE
        xfermode = PorterDuffXfermode(PorterDuff.Mode.DST_OUT)
//        isDither=true
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

        if(output.length>=2){
        for (i in 0..(output.length-2)){
            when {
                output[i] =='1' -> {
                    drawLinePoints[0]=mRect.width()/6
                    drawLinePoints[1]=mRect.height()/6
                }
                output[i] =='2' -> {
                    drawLinePoints[0]=mRect.width()/2
                    drawLinePoints[1]=mRect.height()/6
                }
                output[i] =='3' -> {
                    drawLinePoints[0]=mRect.width()-mRect.width()/6
                    drawLinePoints[1]=mRect.height()/6
                }
                output[i] =='4' -> {
                    drawLinePoints[0]=mRect.width()/6
                    drawLinePoints[1]=mRect.height()/2
                }
                output[i] =='5' -> {
                    drawLinePoints[0]=mRect.width()/2
                    drawLinePoints[1]=mRect.height()/2
                }
                output[i] =='6' -> {
                    drawLinePoints[0]=mRect.width()-mRect.width()/6
                    drawLinePoints[1]=mRect.height()/2
                }
                output[i] =='7' -> {
                    drawLinePoints[0]=mRect.width()/6
                    drawLinePoints[1]=mRect.height()-mRect.height()/6
                }
                output[i] =='8' -> {
                    drawLinePoints[0]=mRect.width()/2
                    drawLinePoints[1]=mRect.height()-mRect.height()/6
                }
                output[i] =='9' -> {
                    drawLinePoints[0]=mRect.width()-mRect.width()/6
                    drawLinePoints[1]=mRect.height()-mRect.height()/6
                }
            }

            when {
                output[i+1] =='1' -> {
                    drawLinePoints[2]=mRect.width()/6
                    drawLinePoints[3]=mRect.height()/6
                }
                output[i+1] =='2' -> {
                    drawLinePoints[2]=mRect.width()/2
                    drawLinePoints[3]=mRect.height()/6
                }
                output[i+1] =='3' -> {
                    drawLinePoints[2]=mRect.width()-mRect.width()/6
                    drawLinePoints[3]=mRect.height()/6
                }
                output[i+1] =='4' -> {
                    drawLinePoints[2]=mRect.width()/6
                    drawLinePoints[3]=mRect.height()/2
                }
                output[i+1] =='5' -> {
                    drawLinePoints[2]=mRect.width()/2
                    drawLinePoints[3]=mRect.height()/2
                }
                output[i+1] =='6' -> {
                    drawLinePoints[2]=mRect.width()-mRect.width()/6
                    drawLinePoints[3]=mRect.height()/2
                }
                output[i+1] =='7' -> {
                    drawLinePoints[2]=mRect.width()/6
                    drawLinePoints[3]=mRect.height()-mRect.height()/6
                }
                output[i+1] =='8' -> {
                    drawLinePoints[2]=mRect.width()/2
                    drawLinePoints[3]=mRect.height()-mRect.height()/6
                }
                output[i+1] =='9' -> {
                    drawLinePoints[2]=mRect.width()-mRect.width()/6
                    drawLinePoints[3]=mRect.height()-mRect.height()/6
                }
            }

            mCanvas?.drawLine(drawLinePoints[0],drawLinePoints[1],drawLinePoints[2],drawLinePoints[3],paint)
        }
        }

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
            }

            MotionEvent.ACTION_MOVE -> {
                if(last==1 && currentX!=0f){
                    mCanvas?.drawPath(mPath,paintWhite)
                    invalidate()
                    mPath.reset()
                    mPath.moveTo(mRect.width()/6,mRect.height()/6)
                    mPath.lineTo(motionTouchEventX,motionTouchEventY)
                    mCanvas?.drawPath(mPath,paint)
                    invalidate()
                }
                else if(last==2 && currentX!=0f){
                    mCanvas?.drawPath(mPath,paintWhite)
                    invalidate()
                    mPath.reset()
                    mPath.moveTo(mRect.width()/2,mRect.height()/6)
                    mPath.lineTo(motionTouchEventX,motionTouchEventY)
                    mCanvas?.drawPath(mPath,paint)
                    invalidate()
                }
                else if(last==3 && currentX!=0f){
                    mCanvas?.drawPath(mPath,paintWhite)
                    invalidate()
                    mPath.reset()
                    mPath.moveTo(mRect.width()-mRect.width()/6,mRect.height()/6)
                    mPath.lineTo(motionTouchEventX,motionTouchEventY)
                    mCanvas?.drawPath(mPath,paint)
                    invalidate()
                }
                else if(last==4 && currentX!=0f){
                    mCanvas?.drawPath(mPath,paintWhite)
                    invalidate()
                    mPath.reset()
                    mPath.moveTo(mRect.width()/6,mRect.height()/2)
                    mPath.lineTo(motionTouchEventX,motionTouchEventY)
                    mCanvas?.drawPath(mPath,paint)
                    invalidate()
                }
                else if(last==5 && currentX!=0f){
                    mCanvas?.drawPath(mPath,paintWhite)
                    invalidate()
                    mPath.reset()
                    mPath.moveTo(mRect.width()/2,mRect.height()/2)
                    mPath.lineTo(motionTouchEventX,motionTouchEventY)
                    mCanvas?.drawPath(mPath,paint)
                    invalidate()
                }
                else if(last==6 && currentX!=0f){
                    mCanvas?.drawPath(mPath,paintWhite)
                    invalidate()
                    mPath.reset()
                    mPath.moveTo(mRect.width()-mRect.width()/6,mRect.height()/2)
                    mPath.lineTo(motionTouchEventX,motionTouchEventY)
                    mCanvas?.drawPath(mPath,paint)
                    invalidate()
                }
                else if(last==7 && currentX!=0f){
                    mCanvas?.drawPath(mPath,paintWhite)
                    invalidate()
                    mPath.reset()
                    mPath.moveTo(mRect.width()/6,mRect.height()-mRect.height()/6)
                    mPath.lineTo(motionTouchEventX,motionTouchEventY)
                    mCanvas?.drawPath(mPath,paint)
                    invalidate()
                }
                else if(last==8 && currentX!=0f){
                    mCanvas?.drawPath(mPath,paintWhite)
                    invalidate()
                    mPath.reset()
                    mPath.moveTo(mRect.width()/2,mRect.height()-mRect.height()/6)
                    mPath.lineTo(motionTouchEventX,motionTouchEventY)
                    mCanvas?.drawPath(mPath,paint)
                    invalidate()
                }
                else if(last==9 && currentX!=0f){
                    mCanvas?.drawPath(mPath,paintWhite)
                    invalidate()
                    mPath.reset()
                    mPath.moveTo(mRect.width()-mRect.width()/6,mRect.height()-mRect.height()/6)
                    mPath.lineTo(motionTouchEventX,motionTouchEventY)
                    mCanvas?.drawPath(mPath,paint)
                    invalidate()
                }

                //start one
                if ((mRect.width()/12)*1<motionTouchEventX&&motionTouchEventX<(mRect.width()/12)*3
                    && (mRect.height()/12)*1<motionTouchEventY&&motionTouchEventY<(mRect.height()/12)*3
                    && one){
                    if(currentX==0f || currentY==0f){
                        currentX = mRect.width()/6
                        currentY = mRect.height()/6
                    }
                    if(last==3){
                        if(two){
                            two=false
                            output.append(2)
                            mCanvas?.drawLine(currentX,currentY,mRect.width()/2,mRect.height()/6,paint)
                            invalidate()
                            currentX = mRect.width()/2
                            currentY = mRect.height()/6
                        }
                    }else if(last==7){
                        if(four){
                            four=false
                            output.append(4)
                            mCanvas?.drawLine(currentX,currentY,mRect.width()/6,mRect.height()/2,paint)
                            invalidate()
                            currentX = mRect.width()/6
                            currentY = mRect.height()/2
                        }
                    }else if (last==9){
                        if(five){
                            five=false
                            output.append(5)
                            mCanvas?.drawLine(currentX,currentY,mRect.width()/2,mRect.height()/2,paint)
                            invalidate()
                            currentX = mRect.width()/2
                            currentY = mRect.height()/2
                        }
                    }
                    one=false
                    output.append(1)
                    last=1
                    mCanvas?.drawLine(currentX,currentY,mRect.width()/6,mRect.height()/6,paint)
                    invalidate()
                    currentX = mRect.width()/6
                    currentY = mRect.height()/6
                }
                //End One



                //start two
                else if ((mRect.width()/12)*5<motionTouchEventX&&motionTouchEventX<(mRect.width()/12)*7
                    && (mRect.height()/12)*1<motionTouchEventY&&motionTouchEventY<(mRect.height()/12)*3
                    && two){
                    if(currentX==0f || currentY==0f){
                        currentX = mRect.width()/2
                        currentY = mRect.height()/6
                    }
                    if (last==8){
                        if(five){
                            five=false
                            output.append(5)
                            mCanvas?.drawLine(currentX,currentY,mRect.width()/2,mRect.height()/2,paint)
                            invalidate()
                            currentX = mRect.width()/2
                            currentY = mRect.height()/2
                        }
                    }
                    two=false
                    output.append(2)
                    last=2
                    mCanvas?.drawLine(currentX,currentY,mRect.width()/2,mRect.height()/6,paint)
                    invalidate()
                    currentX = mRect.width()/2
                    currentY = mRect.height()/6
                }
                //End two



                //start three
                else if ((mRect.width()/12)*9<motionTouchEventX&&motionTouchEventX<(mRect.width()/12)*11
                    && (mRect.height()/12)*1<motionTouchEventY&&motionTouchEventY<(mRect.height()/12)*3
                    && three){
                    if(currentX==0f || currentY==0f){
                        currentX = mRect.width()-mRect.width()/6
                        currentY = mRect.height()/6
                    }
                    if(last==1){
                        if(two){
                            two=false
                            output.append(2)
                            mCanvas?.drawLine(currentX,currentY,mRect.width()/2,mRect.height()/6,paint)
                            invalidate()
                            currentX = mRect.width()/2
                            currentY = mRect.height()/6
                        }
                    }else if(last==7){
                        if(five){
                            five=false
                            output.append(5)
                            mCanvas?.drawLine(currentX,currentY,mRect.width()/2,mRect.height()/2,paint)
                            invalidate()
                            currentX = mRect.width()/2
                            currentY = mRect.height()/2
                        }

                    }else if(last==9){
                        if(six){
                            six=false
                            output.append(6)
                            mCanvas?.drawLine(currentX,currentY,mRect.width()-mRect.width()/6,mRect.height()/2,paint)
                            invalidate()
                            currentX = mRect.width()-mRect.width()/6
                            currentY = mRect.height()/2
                        }
                    }
                    three=false
                    output.append(3)
                    last=3
                    mCanvas?.drawLine(currentX,currentY,mRect.width()-mRect.width()/6,mRect.height()/6,paint)
                    invalidate()
                    currentX = mRect.width()-mRect.width()/6
                    currentY = mRect.height()/6
                }
                //End three



                //start four
                else if ((mRect.width()/12)*1<motionTouchEventX&&motionTouchEventX<(mRect.width()/12)*3
                    && (mRect.height()/12)*5<motionTouchEventY&&motionTouchEventY<(mRect.height()/12)*7
                    && four){
                    if(currentX==0f || currentY==0f){
                        currentX = mRect.width()/6
                        currentY = mRect.height()/2
                    }
                    if(last==6){
                        if(five){
                            five=false
                            output.append(5)
                            mCanvas?.drawLine(currentX,currentY,mRect.width()/2,mRect.height()/2,paint)
                            invalidate()
                            currentX = mRect.width()/2
                            currentY = mRect.height()/2
                        }
                    }
                    four=false
                    output.append(4)
                    last=4
                    mCanvas?.drawLine(currentX,currentY,mRect.width()/6,mRect.height()/2,paint)
                    invalidate()
                    currentX = mRect.width()/6
                    currentY = mRect.height()/2
                }
                //End four



                //start five
                else if ((mRect.width()/12)*5<motionTouchEventX&&motionTouchEventX<(mRect.width()/12)*7
                    && (mRect.height()/12)*5<motionTouchEventY&&motionTouchEventY<(mRect.height()/12)*7
                    && five){
                    if(currentX==0f || currentY==0f){
                        currentX = mRect.width()/2
                        currentY = mRect.height()/2
                    }
                    five=false
                    output.append(5)
                    last=5
                    mCanvas?.drawLine(currentX,currentY,mRect.width()/2,mRect.height()/2,paint)
                    invalidate()
                    currentX = mRect.width()/2
                    currentY = mRect.height()/2
                }
                //End five



                //start six
                else if ((mRect.width()/12)*9<motionTouchEventX&&motionTouchEventX<(mRect.width()/12)*11
                    && (mRect.height()/12)*5<motionTouchEventY&&motionTouchEventY<(mRect.height()/12)*7
                    && six){
                    if(currentX==0f || currentY==0f){
                        currentX = mRect.width()-mRect.width()/6
                        currentY = mRect.height()/2
                    }
                    if(last==4){
                        if(five){
                            five=false
                            output.append(5)
                            mCanvas?.drawLine(currentX,currentY,mRect.width()/2,mRect.height()/2,paint)
                            invalidate()
                            currentX = mRect.width()/2
                            currentY = mRect.height()/2
                        }
                    }
                    six=false
                    output.append(6)
                    last=6
                    mCanvas?.drawLine(currentX,currentY,mRect.width()-mRect.width()/6,mRect.height()/2,paint)
                    invalidate()
                    currentX = mRect.width()-mRect.width()/6
                    currentY = mRect.height()/2
                }
                //End six



                //start seven
                else if ((mRect.width()/12)*1<motionTouchEventX&&motionTouchEventX<(mRect.width()/12)*3
                    && (mRect.height()/12)*9<motionTouchEventY&&motionTouchEventY<(mRect.height()/12)*11
                    && seven){
                    if(currentX==0f || currentY==0f){
                        currentX = mRect.width()/6
                        currentY = mRect.height()-mRect.height()/6
                    }
                    if(last==1){
                        if(four){
                            four=false
                            output.append(4)
                            mCanvas?.drawLine(currentX,currentY,mRect.width()/6,mRect.height()/2,paint)
                            invalidate()
                            currentX = mRect.width()/6
                            currentY = mRect.height()/2
                        }
                    }else if(last==3){
                        if(five){
                            five=false
                            output.append(5)
                            mCanvas?.drawLine(currentX,currentY,mRect.width()/2,mRect.height()/2,paint)
                            invalidate()
                            currentX = mRect.width()/2
                            currentY = mRect.height()/2
                        }
                    }else if(last==9){
                        if(eight){
                            eight=false
                            output.append(8)
                            mCanvas?.drawLine(currentX,currentY,mRect.width()/2,mRect.height()-mRect.height()/6,paint)
                            invalidate()
                            currentX = mRect.width()/2
                            currentY = mRect.height()-mRect.height()/6
                        }
                    }
                    seven=false
                    output.append(7)
                    last=7
                    mCanvas?.drawLine(currentX,currentY,mRect.width()/6,mRect.height()-mRect.height()/6,paint)
                    invalidate()
                    currentX = mRect.width()/6
                    currentY = mRect.height()-mRect.height()/6
                }
                //End seven



                //start eight
                else if ((mRect.width()/12)*5<motionTouchEventX&&motionTouchEventX<(mRect.width()/12)*7
                    && (mRect.height()/12)*9<motionTouchEventY&&motionTouchEventY<(mRect.height()/12)*11
                    && eight){
                    if(currentX==0f || currentY==0f){
                        currentX = mRect.width()/2
                        currentY = mRect.height()-mRect.height()/6
                    }
                    if(last==2){
                        if(five){
                            five=false
                            output.append(5)
                            mCanvas?.drawLine(currentX,currentY,mRect.width()/2,mRect.height()/2,paint)
                            invalidate()
                            currentX = mRect.width()/2
                            currentY = mRect.height()/2
                        }
                    }
                    eight=false
                    output.append(8)
                    last=8
                    mCanvas?.drawLine(currentX,currentY,mRect.width()/2,mRect.height()-mRect.height()/6,paint)
                    invalidate()
                    currentX = mRect.width()/2
                    currentY = mRect.height()-mRect.height()/6
                }
                //End eight



                //start nine
                else if ((mRect.width()/12)*9<motionTouchEventX&&motionTouchEventX<(mRect.width()/12)*11
                    && (mRect.height()/12)*9<motionTouchEventY&&motionTouchEventY<(mRect.height()/12)*11
                    && nine){
                    if(currentX==0f || currentY==0f){
                        currentX = mRect.width()-mRect.width()/6
                        currentY = mRect.height()-mRect.height()/6
                    }
                    if(last==1){
                        if(five){
                            five=false
                            output.append(5)
                            mCanvas?.drawLine(currentX,currentY,mRect.width()/2,mRect.height()/2,paint)
                            invalidate()
                            currentX = mRect.width()/2
                            currentY = mRect.height()/2
                        }
                    }else if(last==3){
                        if(six){
                            six=false
                            output.append(6)
                            mCanvas?.drawLine(currentX,currentY,mRect.width()-mRect.width()/6,mRect.height()/2,paint)
                            invalidate()
                            currentX = mRect.width()-mRect.width()/6
                            currentY = mRect.height()/2
                        }
                    }else if(last==7){
                        if(eight){
                            eight=false
                            output.append(8)
                            mCanvas?.drawLine(currentX,currentY,mRect.width()/2,mRect.height()-mRect.height()/6,paint)
                            invalidate()
                            currentX = mRect.width()/2
                            currentY = mRect.height()-mRect.height()/6
                        }
                    }
                    nine=false
                    output.append(9)
                    last=9
                    mCanvas?.drawLine(currentX,currentY,mRect.width()-mRect.width()/6,mRect.height()-mRect.height()/6,paint)
                    invalidate()
                    currentX = mRect.width()-mRect.width()/6
                    currentY = mRect.height()-mRect.height()/6
                }
                //End nine


            }

            MotionEvent.ACTION_UP -> {
//                path.reset()
//                mCanvas?.drawLine(currentX,currentY,motionTouchEventX,motionTouchEventY,paint)
//                invalidate()
                mCanvas?.drawPath(mPath,paintWhite)
                invalidate()
                realoutput=output.toString()

                if(output.length>=2){
                    for (i in 0..(output.length-2)){
                        when {
                            output[i] =='1' -> {
                                drawLinePoints[0]=mRect.width()/6
                                drawLinePoints[1]=mRect.height()/6
                            }
                            output[i] =='2' -> {
                                drawLinePoints[0]=mRect.width()/2
                                drawLinePoints[1]=mRect.height()/6
                            }
                            output[i] =='3' -> {
                                drawLinePoints[0]=mRect.width()-mRect.width()/6
                                drawLinePoints[1]=mRect.height()/6
                            }
                            output[i] =='4' -> {
                                drawLinePoints[0]=mRect.width()/6
                                drawLinePoints[1]=mRect.height()/2
                            }
                            output[i] =='5' -> {
                                drawLinePoints[0]=mRect.width()/2
                                drawLinePoints[1]=mRect.height()/2
                            }
                            output[i] =='6' -> {
                                drawLinePoints[0]=mRect.width()-mRect.width()/6
                                drawLinePoints[1]=mRect.height()/2
                            }
                            output[i] =='7' -> {
                                drawLinePoints[0]=mRect.width()/6
                                drawLinePoints[1]=mRect.height()-mRect.height()/6
                            }
                            output[i] =='8' -> {
                                drawLinePoints[0]=mRect.width()/2
                                drawLinePoints[1]=mRect.height()-mRect.height()/6
                            }
                            output[i] =='9' -> {
                                drawLinePoints[0]=mRect.width()-mRect.width()/6
                                drawLinePoints[1]=mRect.height()-mRect.height()/6
                            }
                        }

                        when {
                            output[i+1] =='1' -> {
                                drawLinePoints[2]=mRect.width()/6
                                drawLinePoints[3]=mRect.height()/6
                            }
                            output[i+1] =='2' -> {
                                drawLinePoints[2]=mRect.width()/2
                                drawLinePoints[3]=mRect.height()/6
                            }
                            output[i+1] =='3' -> {
                                drawLinePoints[2]=mRect.width()-mRect.width()/6
                                drawLinePoints[3]=mRect.height()/6
                            }
                            output[i+1] =='4' -> {
                                drawLinePoints[2]=mRect.width()/6
                                drawLinePoints[3]=mRect.height()/2
                            }
                            output[i+1] =='5' -> {
                                drawLinePoints[2]=mRect.width()/2
                                drawLinePoints[3]=mRect.height()/2
                            }
                            output[i+1] =='6' -> {
                                drawLinePoints[2]=mRect.width()-mRect.width()/6
                                drawLinePoints[3]=mRect.height()/2
                            }
                            output[i+1] =='7' -> {
                                drawLinePoints[2]=mRect.width()/6
                                drawLinePoints[3]=mRect.height()-mRect.height()/6
                            }
                            output[i+1] =='8' -> {
                                drawLinePoints[2]=mRect.width()/2
                                drawLinePoints[3]=mRect.height()-mRect.height()/6
                            }
                            output[i+1] =='9' -> {
                                drawLinePoints[2]=mRect.width()-mRect.width()/6
                                drawLinePoints[3]=mRect.height()-mRect.height()/6
                            }
                        }

                        mCanvas?.drawLine(drawLinePoints[0],drawLinePoints[1],drawLinePoints[2],drawLinePoints[3],paintWhite)
                        invalidate()
                    }
                }



                output.clear()
                Log.d("asdert",""+realoutput)


                currentX=0f
                currentY=0f
                one=true
                two=true
                three=true
                four=true
                five=true
                six=true
                seven=true
                eight=true
                nine=true
                last=0
            }
        }
        return true

    }
}