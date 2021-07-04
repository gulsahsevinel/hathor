package com.gulsah.hathor.repo

import com.gulsah.hathor.entity.CRUDResponse
import com.gulsah.hathor.retrofit.ApiUtils
import com.gulsah.hathor.retrofit.ProductsDaoInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BasketRepo {
    private val pdaoi: ProductsDaoInterface
    init {
        pdaoi = ApiUtils.getProductsDaoInterface()
    }

    fun addBasket(id: Int, itemNumber: Int) {
        pdaoi.addBasket(id, itemNumber).enqueue(object : Callback<CRUDResponse> {
            override fun onResponse(call: Call<CRUDResponse>, response: Response<CRUDResponse>) {
            }

            override fun onFailure(call: Call<CRUDResponse>, t: Throwable) {
            }

        })

    }
}