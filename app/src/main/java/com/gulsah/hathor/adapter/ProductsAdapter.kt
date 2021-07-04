package com.gulsah.hathor.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.RecyclerView
import com.gulsah.hathor.R
import com.gulsah.hathor.ViewModel.BasketViewModel
import com.gulsah.hathor.ViewModel.ProductsViewModel
import com.gulsah.hathor.databinding.ProductsCardViewBinding
import com.gulsah.hathor.entity.Products
import com.gulsah.hathor.fragment.ProductsFragmentDirections
import com.squareup.picasso.Picasso

class ProductsAdapter(
    var mContext: Context,
    var productsList: List<Products>,
    var viewModel: ProductsViewModel
) :
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
        Picasso.get()
            .load("https://drive.google.com/thumbnail?id=${product.urun_gorsel_url}")
            .error(R.drawable.hy_acid)
            .into(holder.cardView.imageViewProductImg)
        holder.cardView.productObject = product

        holder.cardView.imageButtonProductInfo.setOnClickListener {
            val transition = ProductsFragmentDirections.transtionDetails(product)
            Navigation.findNavController(it).navigate(transition)
        }
        holder.cardView.imageButtonProductAddBasket.setOnClickListener {
            viewModel.updateBasket(product.product_id, 1)
        }
    }


    override fun getItemCount(): Int {
        return productsList.size
    }
}