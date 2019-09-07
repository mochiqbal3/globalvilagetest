package com.test.globalvillage.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.test.globalvillage.ProductActivity
import com.test.globalvillage.R
import com.test.globalvillage.model.Product
import com.test.globalvillage.model.Ulasan
import com.test.globalvillage.util.Converter
import kotlinx.android.synthetic.main.layout_ulasan.view.*
import java.util.*


class UlasanAdapter(private val items: MutableList<Ulasan>, val context: Context) : RecyclerView.Adapter<ViewHolderUlasan>() {

    override fun onBindViewHolder(holder: ViewHolderUlasan, position: Int) {
        val ulasan : Ulasan = items.get(position)
        holder.tvNama.text = ulasan.nama
        holder.tvKomentar.text = ulasan.komentar
//        Glide.with(context).load(product.image_url).into(viewHolder.image)
        holder.tvTanggal.text = "- " + ulasan.tanggal
        holder.simpleRatingBar.numStars = ulasan.rating
        holder.simpleRatingBar.isEnabled = false
    }


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolderUlasan {
        return ViewHolderUlasan(
            LayoutInflater.from(context).inflate(
                R.layout.layout_ulasan,
                p0,
                false
            )
        )
    }

    // Gets the number of animals in the list
    override fun getItemCount(): Int {
        return items.size
    }
}

class ViewHolderUlasan(view: View) : RecyclerView.ViewHolder(view) {
    val imageProfile = view.imageProfile
    val tvNama = view.tvNama
    val tvTanggal = view.tvTanggal
    val tvKomentar = view.tvKomentar
    val simpleRatingBar = view.simpleRatingBar
}



