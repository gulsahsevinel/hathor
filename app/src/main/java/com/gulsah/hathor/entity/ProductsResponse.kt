package com.gulsah.hathor.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ProductsResponse (
    @SerializedName("urunler")
    @Expose
    var urunler: List<Products>,
    @SerializedName("success")
    @Expose
    var success: Int
        ){
}