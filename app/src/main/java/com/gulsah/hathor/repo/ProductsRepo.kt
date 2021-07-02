package com.gulsah.hathor.repo

import androidx.lifecycle.MutableLiveData
import com.gulsah.hathor.entity.Products
import com.gulsah.hathor.entity.ProductsResponse
import com.gulsah.hathor.retrofit.ApiUtils
import com.gulsah.hathor.retrofit.ProductsDaoInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductsRepo {

    private val productsList: MutableLiveData<List<Products>>
    private val pdaoi: ProductsDaoInterface

    init {
        pdaoi = ApiUtils.getProductsDaoInterface()
        productsList = MutableLiveData()
    }

    fun productsGet(): MutableLiveData<List<Products>> {
        return productsList
    }

    fun productsShow() {
        pdaoi.getProducts("gulsahsevinel").enqueue(object : Callback<ProductsResponse> {
            override fun onResponse(
                call: Call<ProductsResponse>,
                response: Response<ProductsResponse>
            ) {
                val list = response.body()!!.urunler
                productsList.value = list
            }

            override fun onFailure(call: Call<ProductsResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }
}