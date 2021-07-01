package com.gulsah.hathor.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Users(
    @SerializedName("id")
    @Expose
    var user_id: Int,
    @SerializedName("mail_adres")
    @Expose
    var user_mail: String,
    @SerializedName("sifre")
    @Expose
    var user_password: String,
    @SerializedName("ad_soyad")
    @Expose
    var user_fullName: String,
    @SerializedName("telefon")
    @Expose
    var user_phoneNumber: String,
    @SerializedName("deger")
    @Expose
    var user_value: Int

) : Serializable {
}