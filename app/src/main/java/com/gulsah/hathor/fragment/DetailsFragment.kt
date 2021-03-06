package com.gulsah.hathor.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.gulsah.hathor.R
import com.gulsah.hathor.ViewModel.ProductsViewModel
import com.gulsah.hathor.databinding.FragmentDetailsBinding
import com.squareup.picasso.Picasso

class DetailsFragment : Fragment() {

    private lateinit var layout: FragmentDetailsBinding
    private lateinit var viewModel: ProductsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        layout = DataBindingUtil.inflate(inflater, R.layout.fragment_details, container, false)

        val bundle : DetailsFragmentArgs by navArgs()
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
        layout.buttonAddBasket.setOnClickListener {
            viewModel.updateBasket(productObject.product_id, 1)
            Toast.makeText(requireContext(), getString(R.string.add_basket), Toast.LENGTH_SHORT)
                .show()
        }

        return layout.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val temp: ProductsViewModel by viewModels()
        viewModel = temp
    }
}

