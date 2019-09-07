package com.test.globalvillage.adapter

import android.content.Context
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.test.globalvillage.ProductActivity
import com.test.globalvillage.R
import com.test.globalvillage.model.Product
import com.test.globalvillage.util.Converter
import kotlinx.android.synthetic.main.card_product.view.*
import java.util.*


class ProductAdapter(private val items: MutableList<Product>, val context: Context) : RecyclerView.Adapter<ViewHolder>() {
    private val arrayListProduct = mutableListOf<Product>()

    init {
        this.arrayListProduct.addAll(items)
    }
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.card_product,
                p0,
                false
            )
        )
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val product: Product = items.get(position)
        viewHolder.tvNamaBarang.text = product.name
        viewHolder.tvNama.text = product.user.name
        viewHolder.tvHarga.text = Converter.rupiah(product.price.toDouble()) + "/" + product.unit.name
        Glide.with(context).load(product.image_url).into(viewHolder.image)
        viewHolder.tvLocation.text = product.region.vilage + ", " +  product.region.province
        viewHolder.itemView.setOnClickListener { v ->
            val gson = Gson()
            context.startActivity(ProductActivity.newInstance(context,gson.toJson(product)))
        }
    }

    // Gets the number of animals in the list
    override fun getItemCount(): Int {
        return items.size
    }

    fun filter(charText: String) {
        var charText = charText
        charText = charText.toLowerCase(Locale.getDefault())
        if (charText.length == 0) {
            items.clear()
            items.addAll(arrayListProduct)
        } else {
            items.clear()
            for (product in arrayListProduct) {
                if (product.name.toLowerCase(Locale.getDefault())
                        .contains(charText)) {
                    items.add(product)
                }
            }
        }
        notifyDataSetChanged()
    }
}

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val image = view.image
    val tvNamaBarang = view.tvNamaBarang
    val tvNama = view.tvNamaUser
    val tvHarga = view.tvHarga
    val tvLocation = view.tvLocation
}



