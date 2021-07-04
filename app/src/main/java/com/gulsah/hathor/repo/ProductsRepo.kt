package com.gulsah.hathor.repo

import androidx.lifecycle.MutableLiveData
import com.gulsah.hathor.entity.CRUDResponse
import com.gulsah.hathor.entity.Products
import com.gulsah.hathor.entity.ProductsResponse
import com.gulsah.hathor.retrofit.ApiUtils
import com.gulsah.hathor.retrofit.ProductsDaoInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductsRepo {

    private val productsList: MutableLiveData<List<Products>>
    private val offersList: MutableLiveData<List<Products>>
    private val pdaoi: ProductsDaoInterface

    init {
        pdaoi = ApiUtils.getProductsDaoInterface()
        productsList = MutableLiveData()
        offersList = MutableLiveData()
    }

    fun productsGet(): MutableLiveData<List<Products>> {
        return productsList
    }

    fun offersGet(): MutableLiveData<List<Products>> {
        return offersList
    }

    fun productsShow() {
        pdaoi.getProducts("gulsahsevinel").enqueue(object : Callback<ProductsResponse> {
            override fun onResponse(
                call: Call<ProductsResponse>,
                response: Response<ProductsResponse>
            ) {
                val list = response.body()!!.urunler
                val tempList = ArrayList<Products>()

                for (p in 0 until list.size) {
                    if (list[p].urun_indirimli_mi == 0) {
                        tempList.add(list[p])
                    }
                    productsList.value = tempList
                }
            }

            override fun onFailure(call: Call<ProductsResponse>, t: Throwable) {
            }
        })
    }

    fun offersShow() {
        pdaoi.getProducts("gulsahsevinel").enqueue(object : Callback<ProductsResponse> {
            override fun onResponse(
                call: Call<ProductsResponse>,
                response: Response<ProductsResponse>
            ) {
                val list = response.body()!!.urunler
                val tempList = ArrayList<Products>()
                for (p in 0 until list.size) {
                    if (list[p].urun_indirimli_mi == 1) {
                        tempList.add(list[p])
                    }
                    offersList.value = tempList
                }
            }

            override fun onFailure(call: Call<ProductsResponse>, t: Throwable) {
            }

        })
    }


}