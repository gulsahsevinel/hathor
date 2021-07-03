package com.gulsah.hathor.adapter

import android.content.Context
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gulsah.hathor.databinding.OffersCardViewBinding
import com.gulsah.hathor.entity.Products
import com.squareup.picasso.Picasso


class OffersAdapter(var mContext: Context, var offersList: List<Products>) :
    RecyclerView.Adapter<OffersAdapter.OffersCardHolder>() {

    inner class OffersCardHolder(offersCardViewBinding: OffersCardViewBinding) :
        RecyclerView.ViewHolder(offersCardViewBinding.root) {
        var cardView: OffersCardViewBinding

        init {
            this.cardView = offersCardViewBinding
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OffersCardHolder {
        val layoutInflater = LayoutInflater.from(mContext)
        val layout = OffersCardViewBinding.inflate(layoutInflater, parent, false)
        return OffersCardHolder(layout)
    }

    override fun onBindViewHolder(holder: OffersCardHolder, position: Int) {

        var discount = listOf<Double>(99.99, 78.99, 99.99, 79.88)

        holder.cardView.textViewProductPrice.apply {
            paintFlags = paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        }
        val product = offersList.get(position)

        Picasso.get()
            .load("https://drive.google.com/thumbnail?id=${product.urun_gorsel_url}")
            .into(holder.cardView.imageViewProductImg)
        holder.cardView.discount.text = "₺ ${discount[position]}"

        holder.cardView.productObject = product

    }

    override fun getItemCount(): Int {
        return offersList.size

    }
}