package com.gulsah.hathor.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gulsah.hathor.entity.Products
import com.gulsah.hathor.repo.ProductsRepo

class ProductsViewModel : ViewModel() {

    var productsList = MutableLiveData<List<Products>>()
    var offersList = MutableLiveData<List<Products>>()
    val pdaor = ProductsRepo()

    init {
        productsLoad()
        offersLoad()
        productsList = pdaor.productsGet()
        offersList = pdaor.offersGet()
    }

    private fun productsLoad() {
        pdaor.productsShow()
    }private fun offersLoad() {
        pdaor.offersShow()
    }
}