package com.gulsah.hathor.retrofit

import com.gulsah.hathor.entity.CRUDResponse
import com.gulsah.hathor.entity.ProductsResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ProductsDaoInterface {
    @POST("urunler.php")
    @FormUrlEncoded //eklenenlerde karakter düzenlemesi vs?
    fun tumUrunler(
        @Field("satici_adi") satici_adi: String,
    ): Call<ProductsResponse>

    @POST("urun_ekle.php")
    @FormUrlEncoded //eklenenlerde karakter düzenlemesi vs?
    fun urunEkle(
        @Field("satici_adi") satici_adi: String,
        @Field("urun_adi") urun_adi: String,
        @Field("urun_fiyat") urun_fiyat: Double,
        @Field("urun_aciklama") urun_aciklama: String,
        @Field("urun_gorsel_url") urun_gorsel_url: String,
        @Field("urun_indirimli_mi") urun_indirimli_mi: Int,
    ): Call<CRUDResponse>

    @POST("sepet_durum_degistir.php")
    @FormUrlEncoded //eklenenlerde karakter düzenlemesi vs?
    fun sepetDurumDegistir(
        @Field("id") id: Int,
    ): Call<CRUDResponse>
}