package com.gulsah.hathor.adapter

import android.content.Context
import android.graphics.Paint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.gulsah.hathor.BasketViewModel
import com.gulsah.hathor.R
import com.gulsah.hathor.databinding.BasketCardViewBinding
import com.gulsah.hathor.entity.Products
import com.squareup.picasso.Picasso

class BasketAdapter(
    var mContext: Context,
    var basketList: List<Products>,
    var viewModel: BasketViewModel,
) :
    RecyclerView.Adapter<BasketAdapter.BasketCardHolder>() {

    inner class BasketCardHolder(basketCardViewBinding: BasketCardViewBinding) :
        RecyclerView.ViewHolder(basketCardViewBinding.root) {
        var cardView: BasketCardViewBinding


        init {
            this.cardView = basketCardViewBinding
        }

    }

    var totalPrice: Double

    init {
        totalPrice = 0.0
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasketCardHolder {
        val layoutInflater = LayoutInflater.from(mContext)
        val layout = BasketCardViewBinding.inflate(layoutInflater, parent, false)
        return BasketCardHolder(layout)
    }

    override fun onBindViewHolder(holder: BasketCardHolder, position: Int) {
        val product = basketList.get(position)
        Picasso.get()
            .load("https://drive.google.com/thumbnail?id=${product.urun_gorsel_url}")
            .error(R.drawable.hy_acid)
            .into(holder.cardView.imageViewProduct)

        totalPrice = totalPrice + product.urun_fiyat

        if (product.urun_indirimli_mi == 1) {
            holder.cardView.discount.text = "₺ 79.88"

            holder.cardView.textViewPrice.apply {
                paintFlags = paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            }
            totalPrice = totalPrice - product.urun_fiyat + (product.urun_fiyat - discount[position])
        }
        holder.cardView.productObject = product
        holder.cardView.imageButtonRemoveItem.setOnClickListener {
            viewModel.updateBasket(product.product_id, 0)
            viewModel.getBasket()
        }
    }

    override fun getItemCount(): Int {
        return basketList.size
    }
}