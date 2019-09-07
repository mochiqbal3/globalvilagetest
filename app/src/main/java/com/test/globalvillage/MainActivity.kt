package com.test.globalvillage

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.test.globalvillage.adapter.ProductAdapter
import com.test.globalvillage.model.ResponseProduct
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var productAdapter: ProductAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initUi()
        initEvent()
        getProduct()
    }

    fun initUi(){
        setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.title = "Baru Panen"
            actionBar.setDisplayShowHomeEnabled(true)
        }
        recyclerView.layoutManager = GridLayoutManager(applicationContext,2)
    }

    fun initEvent(){
        etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                productAdapter.filter(s.toString())
            }

            override fun afterTextChanged(s: Editable) {

            }
        })
    }

    fun getProduct(){
        ResponseProduct.getProduct(6,0).subscribe(
            { success ->
                productAdapter = ProductAdapter(success.dataProduct,applicationContext)
                recyclerView.adapter = productAdapter
            },
            { error ->
                Log.e("error",error.message)
                Toast.makeText(applicationContext,"Periksa koneksi anda", Toast.LENGTH_SHORT).show()
            }
        )
    }
}
