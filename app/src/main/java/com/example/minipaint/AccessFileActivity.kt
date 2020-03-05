package com.example.minipaint

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import kotlinx.android.synthetic.main.activity_access_file.*

class AccessFileActivity : AppCompatActivity() {

    var filePath="asd.jpg"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_access_file)

        filePath=Environment.getExternalStorageDirectory().absolutePath+"/Aman/asd.jpg"

        val bitmap=BitmapFactory.decodeFile(filePath)
        image_view.setImageBitmap(bitmap)
        Log.d("asdfgh",""+getExternalFilesDir(null))
        val df=MediaStore.Files()
    }
}
