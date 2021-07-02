package com.gulsah.hathor

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gulsah.hathor.databinding.ProductsCardViewBinding
import com.gulsah.hathor.entity.Products
import com.squareup.picasso.Picasso

class ProductsAdapter(var mContext: Context, var productsList: List<Products>) :
    RecyclerView.Adapter<ProductsAdapter.ProductsCardHolder>() {

    inner class ProductsCardHolder(productsCardViewBinding: ProductsCardViewBinding) :
        RecyclerView.ViewHolder(productsCardViewBinding.root) {
        var cardView: ProductsCardViewBinding

        init {
            this.cardView = productsCardViewBinding
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsCardHolder {
        val layoutInflater = LayoutInflater.from(mContext)
        val layout = ProductsCardViewBinding.inflate(layoutInflater, parent, false)
        return ProductsCardHolder(layout)
    }

    override fun onBindViewHolder(holder: ProductsCardHolder, position: Int) {
        val product = productsList.get(position)
        val url = "https://drive.google.com/uc?id=" + product.urun_gorsel_url
        Picasso.get()
            .load(url)
            .resize(50, 50)
            .centerCrop()
            .into(holder.cardView.imageViewProductImg)
        holder.cardView.productObject = product
        Log.e("url", url)

    }

    override fun getItemCount(): Int {
        return productsList.size
    }
}