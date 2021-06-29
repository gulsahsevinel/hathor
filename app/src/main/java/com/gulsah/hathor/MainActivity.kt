package com.gulsah.hathor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.gulsah.hathor.entity.*
import com.gulsah.hathor.retrofit.ApiUtils
import com.gulsah.hathor.retrofit.ProductsDaoInterface
import com.gulsah.hathor.retrofit.UsersDaoInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.collections.indexOf as indexOf1

class MainActivity : AppCompatActivity() {

    private lateinit var pdao: ProductsDaoInterface
    private lateinit var udao: UsersDaoInterface
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pdao = ApiUtils.getProductsDaoInterface()
        udao = ApiUtils.getUsersDaoInterface()
        //kayit()
        //sil()
        //tumUrunler()
        //uyeOl()
        giris()

        
    }

    fun uyeOl() {
        udao.uye_ol("gulsahsevinel@gmail.com", "gulsah123", "Gülşah Sevinel", "123456789")
            .enqueue(object : Callback<CRUDResponse>{
                override fun onResponse(
                    call: Call<CRUDResponse>,
                    response: Response<CRUDResponse>
                ) {
                    Log.e("response", response.body()!!.success.toString())
                    Log.e("mesaj", response.body()!!.message)
                }

                override fun onFailure(call: Call<CRUDResponse>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
    }

    fun giris (){
        udao.giris_yap("gulsahsevinel@gmail.com","gulsah123").enqueue(object : Callback<UsersResponse>{
            override fun onResponse(call: Call<UsersResponse>, response: Response<UsersResponse>) {



                if (response == null)
                {
                    Log.e("deger","${response.body()!!.success}")
                }
                else{
                    Log.e("deger","${response.body()!!.success}")
                    Log.e("qq", "${response.body()!!.user}")

                }
            }

            override fun onFailure(call: Call<UsersResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

    fun tumUrunler() {
        pdao.tumUrunler("gulsahsevinel").enqueue(object : Callback<ProductsResponse> {
            override fun onResponse(
                call: Call<ProductsResponse>,
                response: Response<ProductsResponse>
            ) {
                val urunlerListesi = response.body()!!.urunler
                for (k in urunlerListesi) {
                    Log.e("***********", "***********")
                    Log.e(" mesaj", "geldi")
                    Log.e(" adi", k.urun_adi)
                    Log.e(" id", k.product_id.toString())
                    Log.e(" sepet", k.sepet_durum.toString())
                }

            }

            override fun onFailure(call: Call<ProductsResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

    fun kayit() {
        pdao.urunEkle("gulsahsevinel", "ordinary", 78.99, "nullnull", "nullnull", 0)
            .enqueue(object : Callback<CRUDResponse> {
                override fun onResponse(
                    call: Call<CRUDResponse>,
                    response: Response<CRUDResponse>
                ) {
                    Log.e("response", response.body()!!.success.toString())
                    Log.e("mesaj", response.body()!!.message)
                }

                override fun onFailure(call: Call<CRUDResponse>, t: Throwable) {
                    TODO("Not yet implemented")
                }


            })
    }

    fun sil() {
        pdao.sepetDurumDegistir(5).enqueue(object : Callback<CRUDResponse> {
            override fun onResponse(call: Call<CRUDResponse>, response: Response<CRUDResponse>) {
                Log.e("response", response.body()!!.success.toString())
                Log.e("mesaj", response.body()!!.message)
            }

            override fun onFailure(call: Call<CRUDResponse>, t: Throwable) {}
        })
    }
}