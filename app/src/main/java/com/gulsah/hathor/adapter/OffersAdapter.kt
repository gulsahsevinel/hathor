package com.gulsah.hathor.adapter

import android.content.Context
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.gulsah.hathor.R
import com.gulsah.hathor.ViewModel.ProductsViewModel
import com.gulsah.hathor.databinding.OffersCardViewBinding
import com.gulsah.hathor.entity.Products
import com.gulsah.hathor.fragment.OffersDetailsFragmentDirections
import com.gulsah.hathor.fragment.SpecialOffersFragmentDirections
import com.squareup.picasso.Picasso


class OffersAdapter(
    var mContext: Context,
    var offersList: List<Products>,
    var viewModel: ProductsViewModel
) :
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


        holder.cardView.textViewProductPrice.apply {
            paintFlags = paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        }
        val product = offersList.get(position)

        Picasso.get()
            .load("https://drive.google.com/thumbnail?id=${product.urun_gorsel_url}")
            .into(holder.cardView.imageViewProductImg)
        holder.cardView.discount.text = "₺ 79.88"

        holder.cardView.productObject = product
        holder.cardView.imageButtonProductAddBasket.setOnClickListener {
            viewModel.updateBasket(product.product_id, 1)
        }
        holder.cardView.imageViewProductImg.setOnClickListener {
            val transition =
                SpecialOffersFragmentDirections.offertsToDetails(product)
            Navigation.findNavController(it).navigate(transition)
        }

        holder.cardView.imageButtonProductInfo.setOnClickListener {
            val transition =
                SpecialOffersFragmentDirections.offertsToDetails(product)
            Navigation.findNavController(it).navigate(transition)
        }

        holder.cardView.imageButtonProductAddBasket.setOnClickListener {
            viewModel.updateBasket(product.product_id, 1)
            Toast.makeText(
                mContext,
                mContext.getString(R.string.add_basket),
                Toast.LENGTH_SHORT
            ).show()

        }


    }

    override fun getItemCount(): Int {
        return offersList.size

    }
}