package com.gulsah.hathor.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gulsah.hathor.R
import com.gulsah.hathor.databinding.BasketCardViewBinding
import com.gulsah.hathor.entity.Products
import com.squareup.picasso.Picasso

class BasketAdapter(var mContext: Context, var basketList: List<Products>) :
    RecyclerView.Adapter<BasketAdapter.BasketCardHolder>() {

    inner class BasketCardHolder(basketCardViewBinding: BasketCardViewBinding) :
        RecyclerView.ViewHolder(basketCardViewBinding.root) {
        var cardView: BasketCardViewBinding
        init {
            this.cardView = basketCardViewBinding
        }

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
        holder.cardView.productObject = product
    }

    override fun getItemCount(): Int {
        return basketList.size
    }
}