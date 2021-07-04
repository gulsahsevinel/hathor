package com.gulsah.hathor.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.gulsah.hathor.adapter.OffersAdapter
import com.gulsah.hathor.R
import com.gulsah.hathor.ViewModel.ProductsViewModel
import com.gulsah.hathor.databinding.FragmentSpecialOffersBinding
import com.gulsah.hathor.retrofit.ApiUtils
import com.gulsah.hathor.retrofit.ProductsDaoInterface


class SpecialOffersFragment : Fragment() {

    private lateinit var layout: FragmentSpecialOffersBinding
    private lateinit var adapter: OffersAdapter
    private lateinit var viewModel: ProductsViewModel
    private lateinit var pdaoi: ProductsDaoInterface

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        layout =
            DataBindingUtil.inflate(inflater, R.layout.fragment_special_offers, container, false)
        pdaoi = ApiUtils.getProductsDaoInterface()
        layout.offersFragment = this

        layout.rv.layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)

        viewModel.offersList.observe(viewLifecycleOwner) {
            adapter = OffersAdapter(requireContext(), it, viewModel)
            layout.adapter = adapter
        }
        layout.imageViewBasket.setOnClickListener {
            val transition = SpecialOffersFragmentDirections.offersToBasket()
            Navigation.findNavController(it).navigate(transition)
            onDestroy()
        }

        return layout.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val temp: ProductsViewModel by viewModels()
        viewModel = temp
    }

}