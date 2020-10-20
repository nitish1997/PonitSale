package com.sspl.sspls.ponitsale.activity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.sspl.sspls.ponitsale.data.AppDb

open class BaseActivity : AppCompatActivity(), View.OnClickListener {
    public var img_back: ImageView? = null
    //lateinit var db
    override fun onClick(v: View?) {
        TODO("not implemented")
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
       // var db= Room.databaseBuilder(applicationContext,AppDb::class.java,"CategoryDb").build()
    }

}