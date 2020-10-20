package com.sspl.sspls.ponitsale.activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.sspl.sspls.ponitsale.Login
import com.sspl.sspls.ponitsale.R

class UserInfo : BaseActivity() {
    private var btnLogin: Button? = null
    private var btnsubmit: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_info)
        initView()
        setOnClicks()
    }
    private fun setOnClicks() {
        btnLogin?.setOnClickListener(this)
        btnsubmit?.setOnClickListener(this)
    }
    private fun initView() {
        btnsubmit = findViewById(R.id.submit)
        btnLogin = findViewById(R.id.newReg)
    }
    override fun onClick(v: View?) {
        val item_id = v?.id
        when (item_id) {
            R.id.submit -> startActivity(Intent(applicationContext, Login::class.java))
            R.id.newReg -> startActivity(Intent(applicationContext, Login::class.java))
        }
    }
}
