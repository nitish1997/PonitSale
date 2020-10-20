package com.sspl.sspls.ponitsale.activity
import android.content.Intent
import android.os.Bundle
import com.google.android.material.navigation.NavigationView
import androidx.core.view.GravityCompat
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.tabs.TabLayout
import com.sspl.sspls.ponitsale.Login
import com.sspl.sspls.ponitsale.R
import kotlinx.android.synthetic.main.activity_dashboard.*
class Dashboard : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    private var drawer: DrawerLayout? = null
    private var navigationView: NavigationView? = null
    private var toolbar: Toolbar? = null
    private var ll_cat: LinearLayout? = null
    private var ll_group: LinearLayout? = null
    private var ll_product: LinearLayout? = null
    private var ll_priceCatelog: LinearLayout? = null
    private var ll_customer: LinearLayout? = null
    private var ll_suplier: LinearLayout? = null
    private var ll_suplierProduct: LinearLayout? = null
    private var ll_discount: LinearLayout? = null
    private var ll_unit: LinearLayout? = null
    private var ll_table: LinearLayout? = null
    private var ll_department: LinearLayout? = null
    private var ll_rawmaterial: LinearLayout? = null
    private var ll_feedback: LinearLayout? = null
    private var ll_business: LinearLayout? = null
    private var ll_logout: LinearLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        initView()
        setOnClicks()
        setSupportActionBar(toolbar)
        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        nav_view.setNavigationItemSelectedListener(this)
    }
    private fun setOnClicks() {
        ll_customer!!.setOnClickListener(this)
        ll_suplier!!.setOnClickListener(this)
        ll_suplierProduct!!.setOnClickListener(this)
        ll_discount!!.setOnClickListener(this)
        ll_unit!!.setOnClickListener(this)
        ll_table!!.setOnClickListener(this)
        ll_department!!.setOnClickListener(this)
        ll_rawmaterial!!.setOnClickListener(this)
        ll_feedback!!.setOnClickListener(this)
        ll_business!!.setOnClickListener(this)
        ll_logout!!.setOnClickListener(this)
        ll_priceCatelog!!.setOnClickListener(this)
        ll_group!!.setOnClickListener(this)
        ll_product!!.setOnClickListener(this)
        ll_cat!!.setOnClickListener(this)
        navigationView!!.setNavigationItemSelectedListener(this)
    }
    private fun initView() {
        ll_cat = findViewById(R.id.ll_cat)
        toolbar = findViewById(R.id.toolbar)
        drawer = findViewById(R.id.drawer_layout)
        navigationView = findViewById(R.id.nav_view)
        ll_group = findViewById(R.id.ll_group)
        ll_product = findViewById(R.id.ll_product)
        ll_priceCatelog = findViewById(R.id.ll_priceCatelog)
        ll_customer = findViewById(R.id.ll_customer)
        ll_suplier = findViewById(R.id.ll_suplier)
        ll_suplierProduct = findViewById(R.id.ll_suplierProduct)
        ll_discount = findViewById(R.id.ll_discount)
        ll_unit = findViewById(R.id.ll_unit)
        ll_table = findViewById(R.id.ll_table)
        ll_department = findViewById(R.id.ll_department)
        ll_rawmaterial = findViewById(R.id.ll_rawmaterial)
        ll_feedback = findViewById(R.id.ll_feedback)
        ll_business = findViewById(R.id.ll_business)
        ll_logout = findViewById(R.id.ll_logout)
    }
    override fun onClick(v: View?) {
        val item_id = v?.id
        when (item_id) {
            R.id.ll_cat -> startActivity(Intent(applicationContext, ProductCategory::class.java))
            R.id.ll_group -> startActivity(Intent(applicationContext, ProductGroup::class.java))
            R.id.ll_product -> startActivity(Intent(applicationContext, Product::class.java))
            R.id.ll_priceCatelog -> startActivity(Intent(applicationContext, PriceCatalog::class.java))
            R.id.ll_customer -> startActivity(Intent(applicationContext, Customer::class.java))
            R.id.ll_suplier -> startActivity(Intent(applicationContext, Supplier::class.java))
            R.id.ll_suplierProduct -> startActivity(Intent(applicationContext, Supplier::class.java))
            R.id.ll_discount -> startActivity(Intent(applicationContext, Discount::class.java))
            R.id.ll_unit -> startActivity(Intent(applicationContext, Unit::class.java))
            R.id.ll_table -> startActivity(Intent(applicationContext, TabLayout.Tab::class.java))
            R.id.ll_department -> startActivity(Intent(applicationContext, Department::class.java))
            R.id.ll_rawmaterial -> startActivity(Intent(applicationContext, Login::class.java))
            R.id.ll_feedback -> startActivity(Intent(applicationContext, Feedback::class.java))
            R.id.ll_business -> startActivity(Intent(applicationContext, BusinessDetail::class.java))
            R.id.ll_logout -> startActivity(Intent(applicationContext, Login::class.java))
        }
    }
    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
