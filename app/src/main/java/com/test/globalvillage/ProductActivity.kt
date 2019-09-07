package com.test.globalvillage

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.test.globalvillage.adapter.ImagePagerAdapter
import com.test.globalvillage.adapter.UlasanAdapter
import com.test.globalvillage.model.Product
import com.test.globalvillage.model.Ulasan
import com.test.globalvillage.util.Converter
import kotlinx.android.synthetic.main.activity_product.*


val key = "product"

class ProductActivity : AppCompatActivity() {
    var product = Product()
    var ulasans = mutableListOf<Ulasan>()
    companion object {

        fun newInstance(context: Context, jsonProduct: String) =  Intent(context, ProductActivity::class.java).apply {
            putExtra(key, jsonProduct)
            setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)
        initValue()
        initUi()
    }

    fun initUi(){
        tvNamaBarang.text = product.name
        tvHarga.text = Converter.rupiah(product.price.toDouble())
        tvNama.text = product.user.name
        tvBerat.text = product.weight.toString() + " " + product.unit.name
        tvMinimalPemesanan.text = "1 " + product.unit.name
        tvKategori.text = product.category.name
        tvDeskripsi.text = product.description
        val adapter = ImagePagerAdapter(this, product.front_images,applicationContext)
        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager, true)
        var ulasanAdapter = UlasanAdapter(ulasans,applicationContext)
        recyclerView.layoutManager = LinearLayoutManager(applicationContext,RecyclerView.HORIZONTAL,false)
        recyclerView.adapter = ulasanAdapter

    }

    fun initValue(){
        var gson = Gson()
        product = gson.fromJson(intent.getStringExtra(key),Product::class.java)
        var ulasan = Ulasan()
        ulasan.komentar = "Bagus barangnya"
        ulasan.nama = "Jajang"
        ulasan.tanggal = "10 November 2019"
        ulasan.rating = 3
        ulasans.add(ulasan)
        ulasan = Ulasan()
        ulasan.komentar = "Mantap barangnya"
        ulasan.nama = "Budi"
        ulasan.tanggal = "11 November 2019"
        ulasan.rating = 4
        ulasans.add(ulasan)
    }
}
