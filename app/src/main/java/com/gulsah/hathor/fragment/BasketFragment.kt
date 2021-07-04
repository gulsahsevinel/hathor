package com.gulsah.hathor.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gulsah.hathor.R
import com.gulsah.hathor.ViewModel.BasketViewModel
import com.gulsah.hathor.ViewModel.ProductsViewModel
import com.gulsah.hathor.adapter.BasketAdapter
import com.gulsah.hathor.databinding.FragmentBasketBinding


class BasketFragment : Fragment() {

    private lateinit var layout: FragmentBasketBinding
    private lateinit var adapter: BasketAdapter
    private lateinit var viewModel: BasketViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        layout = DataBindingUtil.inflate(inflater, R.layout.fragment_basket, container, false)
        layout.basketFragment = this
        layout.rv.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)

        viewModel.basketList.observe(viewLifecycleOwner) {
            adapter = BasketAdapter(requireContext(), it)
            layout.adapter = adapter
        }

        return layout.root

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val temp: BasketViewModel by viewModels()
        viewModel = temp
    }
}