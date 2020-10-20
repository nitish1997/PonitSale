package com.sspl.sspls.ponitsale.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.sspl.sspls.ponitsale.R

class Feedback : BaseActivity() {
    private var img_happy: ImageView? = null
    private var img_normal: ImageView? = null
    private var img_sad: ImageView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feedback)
        initView()
        setOnClicks()
    }
    private fun setOnClicks() {
        img_back!!.setOnClickListener(this)
        img_happy!!.setOnClickListener(this)
        img_normal!!.setOnClickListener(this)
        img_sad!!.setOnClickListener(this)
    }
    private fun initView() {
        img_back = findViewById(R.id.back)
        img_happy = findViewById(R.id.happy)
        img_normal = findViewById(R.id.normal)
        img_sad = findViewById(R.id.sad)

    }
    override fun onClick(v: View?) {
        val item_id = v?.id
        when (item_id) {
            R.id.back -> finish()
            R.id.happy ->{
                    img_happy!!.setImageDrawable(resources.getDrawable(R.drawable.happygreen))
                    img_normal!!.setImageDrawable(resources.getDrawable(R.drawable.normalblack))
                    img_sad!!. setImageDrawable(resources.getDrawable(R.drawable.sadblack))}
            R.id.normal ->{
                img_happy!!.setImageDrawable(resources.getDrawable(R.drawable.happyblack))
                img_normal!!.setImageDrawable(resources.getDrawable(R.drawable.normalyellow))
                img_sad!!. setImageDrawable(resources.getDrawable(R.drawable.sadblack))}
            R.id.sad ->{
                img_happy!!.setImageDrawable(resources.getDrawable(R.drawable.happyblack))
                img_normal!!.setImageDrawable(resources.getDrawable(R.drawable.normalblack))
                img_sad!!. setImageDrawable(resources.getDrawable(R.drawable.sadred))}
        }
    }
}
