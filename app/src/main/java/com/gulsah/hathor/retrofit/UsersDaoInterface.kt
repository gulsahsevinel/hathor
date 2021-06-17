package com.gulsah.hathor.retrofit

import com.gulsah.hathor.entity.CRUDResponse
import com.gulsah.hathor.entity.UsersResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface UsersDaoInterface {

    @POST("uye_ol.php")
    @FormUrlEncoded
    fun uye_ol(
        @Field("mail_adres") user_mail : String,
        @Field("sifre") user_password : String,
        @Field("ad_soyad") user_fullName : String,
        @Field("telefon") user_phoneNumber : String,
    ) : Call<CRUDResponse>

    @POST("giris_yap.php")
    @FormUrlEncoded
    fun giris_yap(
        @Field("mail_adres") user_mail : String,
        @Field("sifre") user_password : String
    ) : Call<UsersResponse>
}