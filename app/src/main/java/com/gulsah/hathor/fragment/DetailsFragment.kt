package com.gulsah.hathor.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.gulsah.hathor.R
import com.gulsah.hathor.databinding.FragmentDetailsBinding
import com.squareup.picasso.Picasso

class DetailsFragment : Fragment() {

    private lateinit var layout: FragmentDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val manager = requireActivity().supportFragmentManager
        layout = DataBindingUtil.inflate(inflater, R.layout.fragment_details, container, false)
        val bundle: DetailsFragmentArgs by navArgs()
        val productObject = bundle.productObject

        Picasso.get()
            .load("https://drive.google.com/thumbnail?id=${productObject.urun_gorsel_url}")
            .error(R.drawable.hy_acid)
            .into(layout.productImageView)
        layout.productNameTextView.text = productObject.urun_adi
        layout.productPriceTextView.text = "₺ " + productObject.urun_fiyat.toString()
        layout.productDetailsTextView.text = productObject.urun_aciklama
        layout.closeDetailsImageView.setOnClickListener {
            val transition = DetailsFragmentDirections.transitionProducts(productObject)
            Navigation.findNavController(it).navigate(transition)
            onDestroy()

        }



        return layout.root
    }
}