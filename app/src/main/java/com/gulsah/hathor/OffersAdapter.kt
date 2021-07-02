package com.gulsah.hathor

import android.content.Context
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
        val product = offersList.get(position)
        val url = "https://drive.google.com/uc?id=${product.urun_gorsel_url}"
        //Picasso.get().load(url).into(holder.cardView.imageViewProductImg)
        Picasso.get()
            .load(url)
            .resize(80, 100).centerInside()
            .into(holder.cardView.imageViewProductImg)
        holder.cardView.productObject = product


    }

    override fun getItemCount(): Int {
        return 4
    }
}