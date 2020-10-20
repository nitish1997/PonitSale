package com.sspl.sspls.ponitsale.activity
import android.os.Bundle
import android.view.View
import com.sspl.sspls.ponitsale.R

class Discount : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_discount)
        initView()
        setOnClicks()
    }
    private fun setOnClicks() {
        img_back!!.setOnClickListener(this)
    }
    private fun initView() {
        img_back = findViewById(R.id.back)

    }
    override fun onClick(v: View?) {
        val item_id = v?.id
        when (item_id) {
            R.id.back -> finish()
        }
    }
}
