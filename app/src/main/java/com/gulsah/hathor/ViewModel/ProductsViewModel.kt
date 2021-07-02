package com.gulsah.hathor.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gulsah.hathor.entity.Products
import com.gulsah.hathor.repo.ProductsRepo

class ProductsViewModel : ViewModel() {

    var productsList = MutableLiveData<List<Products>>()
    val pdaor = ProductsRepo()

    init {
        productsLoad()
        productsList = pdaor.productsGet()
    }

    private fun productsLoad() {
        pdaor.productsShow()
    }
}