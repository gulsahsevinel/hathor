package com.gulsah.hathor.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.gulsah.hathor.R
import com.gulsah.hathor.ViewModel.ProductsViewModel
import com.gulsah.hathor.ProductsAdapter
import com.gulsah.hathor.databinding.FragmentProductsBinding
import com.gulsah.hathor.retrofit.ApiUtils
import com.gulsah.hathor.retrofit.ProductsDaoInterface


class ProductsFragment : Fragment() {

    private lateinit var layout: FragmentProductsBinding
    private lateinit var adapter: ProductsAdapter
    private lateinit var viewModel: ProductsViewModel
    private lateinit var pdaoi: ProductsDaoInterface

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        layout = DataBindingUtil.inflate(inflater, R.layout.fragment_products, container, false)
        pdaoi = ApiUtils.getProductsDaoInterface()
        layout.productsFragment = this
        layout.rv.layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)

        viewModel.productsList.observe(viewLifecycleOwner) {
            adapter = ProductsAdapter(requireContext(), it)
            layout.adapter = adapter
        }
        return layout.root
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val temp: ProductsViewModel by viewModels()
        viewModel = temp
    }


}