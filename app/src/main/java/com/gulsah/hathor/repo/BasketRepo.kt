package com.gulsah.hathor.repo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.gulsah.hathor.entity.CRUDResponse
import com.gulsah.hathor.entity.Products
import com.gulsah.hathor.entity.ProductsResponse
import com.gulsah.hathor.retrofit.ApiUtils
import com.gulsah.hathor.retrofit.ProductsDaoInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BasketRepo {
    var basketList = MutableLiveData<List<Products>>()

    private val pdaoi: ProductsDaoInterface

    init {
        pdaoi = ApiUtils.getProductsDaoInterface()
        basketList = MutableLiveData()
    }

    fun basketGet(): MutableLiveData<List<Products>> {
        return basketList
    }

    fun basketShow() {
        pdaoi.getProducts("gulsahsevinel").enqueue(object : Callback<ProductsResponse> {
            override fun onResponse(
                call: Call<ProductsResponse>,
                response: Response<ProductsResponse>
            ) {
                val list = response.body()!!.urunler
                val tempList = ArrayList<Products>()
                for (p in 0 until list.size) {
                    if (list[p].sepet_durum == 1) {
                        tempList.add(list[p])
                    }
                    basketList.value = tempList
                }
            }

            override fun onFailure(call: Call<ProductsResponse>, t: Throwable) {
            }

        })
    }

    fun updateBasket(id: Int, basketStatus: Int) {
        pdaoi.addBasket(id, basketStatus).enqueue(object : Callback<CRUDResponse> {
            override fun onResponse(call: Call<CRUDResponse>, response: Response<CRUDResponse>) {
                Log.e("response", response.body()!!.success.toString())
                Log.e("mesaj", response.body()!!.message)
            }

            override fun onFailure(call: Call<CRUDResponse>, t: Throwable) {
                Log.e("response", "başarısız")
            }

        })

    }
}