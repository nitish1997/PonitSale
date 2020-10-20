package com.sspl.sspls.ponitsale.activity
import android.Manifest
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.google.android.material.textfield.TextInputEditText
import com.sspl.sspls.ponitsale.R
import com.sspl.sspls.ponitsale.data.AppDb
import com.sspl.sspls.ponitsale.entity.Category_Entity
import android.annotation.SuppressLint
import androidx.core.app.ActivityCompat
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.provider.MediaStore
import android.util.Base64
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.sspl.sspls.ponitsale.adapter.CategoryAdapter
import com.sspl.sspls.ponitsale.model.CategoryModel
import java.io.ByteArrayOutputStream
class ProductCategory : BaseActivity() {
    private var btnSave: TextView? = null
    private var cat_image: ImageView? = null
    private var close: ImageView? = null
    private var catname: TextInputEditText? = null
    private var Description: TextInputEditText? = null

    private var recyclerView: RecyclerView? = null

    private var cattype: Spinner? = null
    private var disname: Spinner? = null
    private var list: RecyclerView? = null
    lateinit var catName: String
    lateinit var catType: String
    lateinit var disName: String
    lateinit var catImg: String
    lateinit var desc: String
    private val REQUEST_PERMISSION = 100
    private val REQUEST_IMAGE_CAPTURE = 1
    private val REQUEST_PICK_IMAGE = 2
    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_category)
        initView()
        setOnClicks()
        Thread{
            fetchData()
        }.start()

    }
    private fun setOnClicks() {
        img_back!!.setOnClickListener(this)
        close!!.setOnClickListener(this)
        btnSave!!.setOnClickListener(this)
        cat_image!!.setOnClickListener(this)
    }
    private fun initView() {
        img_back = findViewById(R.id.back)
        btnSave = findViewById(R.id.save)
        cat_image = findViewById(R.id.image)
        close = findViewById(R.id.close)
        catname = findViewById(R.id.catname)
        Description = findViewById(R.id.description)
        cattype = findViewById(R.id.cattype)
        disname = findViewById(R.id.disname)
        list = findViewById(R.id.list)
        recyclerView=findViewById(R.id.list)
    }
    override fun onResume() {
        super.onResume()
        checkCameraPermission()
    }
    private fun checkCameraPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.CAMERA),
                REQUEST_PERMISSION)
        }
    }
    @SuppressLint("WrongConstant")
    override fun onClick(v: View?) {
        val item_id = v?.id
        when (item_id) {
            R.id.back -> finish()
            R.id.save ->
            {
                catName=catname?.text.toString()
                catType="Veg"
                disName="10%"
                desc=Description?.text.toString()
                if(catName.trim().length<=0) {
                    Toast.makeText(applicationContext, "Please enter Category name : ", Toast.LENGTH_SHORT).show()
                }else {
                    var db = Room.databaseBuilder(applicationContext, AppDb::class.java, "CategoryDb").build()
                    Thread {
                        var category_Entity = Category_Entity()
                        category_Entity.cat_name = catName
                        category_Entity.cat_type = catType
                        category_Entity.Description = desc
                        category_Entity.discount_name = disName
                        category_Entity.cat_img = catImg
                        db.categoryDao().insert(category_Entity)

                    }.start()
                  //  Thread { fetchData()}.start()
                }
            }
            R.id.image ->
            {
                selectImage(this@ProductCategory)
            }
        }
    }
    @SuppressLint("WrongConstant")
    fun fetchData()
    {
        val categoryList=ArrayList<CategoryModel>()
        var db= Room.databaseBuilder(applicationContext, AppDb ::class.java,"CategoryDb").build()
        db.categoryDao().readValue().forEach {
            categoryList.add(CategoryModel(it.cat_name,it.cat_img))
        }
        recyclerView?.layoutManager=LinearLayoutManager(this,LinearLayout.VERTICAL,false)
        val adapter=CategoryAdapter(categoryList)
        recyclerView?.adapter=adapter
    }
    private fun selectImage(context: Context) {
        val options = arrayOf<CharSequence>("Take Photo", "Choose from Gallery", "Cancel")

        val builder = AlertDialog.Builder(context)
        builder.setTitle("Choose your Category picture")

        builder.setItems(options) { dialog, item ->
            if (options[item] == "Take Photo") {
                Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { intent ->
                    intent.resolveActivity(packageManager)?.also {
                        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE)
                    }
                }

            } else if (options[item] == "Choose from Gallery") {
                val pickPhoto =
                    Intent(Intent.ACTION_GET_CONTENT).also { intent ->
                        intent.type = "image/*"
                        intent.resolveActivity(packageManager)?.also {
                            startActivityForResult(intent, REQUEST_PICK_IMAGE)
                        }
                    }

            } else if (options[item] == "Cancel") {
                dialog.dismiss()
            }
        }
        builder.show()
    }
    @SuppressLint("MissingSuperCall")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_IMAGE_CAPTURE) {
                val bitmap = data?.extras?.get("data") as Bitmap
                cat_image!!.setImageBitmap(bitmap)
                val baos = ByteArrayOutputStream()
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
                val b = baos.toByteArray()
                catImg= Base64.encodeToString(b, Base64.DEFAULT)
            }
            else if (requestCode == REQUEST_PICK_IMAGE) {
                val uri = data?.getData()
                cat_image!!.setImageURI(uri)
                val bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, uri)
                val baos = ByteArrayOutputStream()
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
                val b = baos.toByteArray()
                catImg= Base64.encodeToString(b, Base64.DEFAULT)

            }
        }
    }
}
