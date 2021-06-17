package com.gulsah.hathor.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class UsersResponse (
    @SerializedName("kullanicilar")
    @Expose
    var user: List<Users>,
    @SerializedName("success")
    @Expose
    var success: Int
){
}