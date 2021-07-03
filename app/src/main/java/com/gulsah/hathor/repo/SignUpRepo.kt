package com.gulsah.hathor.repo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.gulsah.hathor.entity.CRUDResponse
import com.gulsah.hathor.entity.Users
import com.gulsah.hathor.retrofit.ApiUtils
import com.gulsah.hathor.retrofit.UsersDaoInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpRepo {

    private val udao: UsersDaoInterface = ApiUtils.getUsersDaoInterface()

    private val success = MutableLiveData<Int>()

    fun getSuccessValue(): MutableLiveData<Int> {
        return success
    }


    fun SignUp(email: String, password: String, fullName: String, phoneNumber: String) {
        udao.uye_ol(email, password, fullName, phoneNumber)
            .enqueue(object : Callback<CRUDResponse> {
                override fun onResponse(
                    call: Call<CRUDResponse>,
                    response: Response<CRUDResponse>
                ) {
                    val u = response.body()!!.success
                    success.value = u
                    Log.e("response", response.body()!!.success.toString())
                    Log.e("mesaj", response.body()!!.message)
                }

                override fun onFailure(call: Call<CRUDResponse>, t: Throwable) {
                    Log.e("response", "başarısız")
                }

            })
    }

}