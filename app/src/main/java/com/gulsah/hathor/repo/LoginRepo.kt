package com.gulsah.hathor.repo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.gulsah.hathor.entity.CRUDResponse
import com.gulsah.hathor.entity.Products
import com.gulsah.hathor.entity.Users
import com.gulsah.hathor.entity.UsersResponse
import com.gulsah.hathor.retrofit.ApiUtils
import com.gulsah.hathor.retrofit.UsersDaoInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginRepo {

    private val userList: MutableLiveData<List<Users>>

    init {
        userList = MutableLiveData()
    }

    private val udao: UsersDaoInterface = ApiUtils.getUsersDaoInterface()

    fun getUser(): MutableLiveData<List<Users>> {
        return userList
    }


    fun SignIn(email: String, password: String) {
        udao.giris_yap(email, password).enqueue(object :
            Callback<UsersResponse> {
            override fun onResponse(call: Call<UsersResponse>, response: Response<UsersResponse>) {

                val u = response.body()!!.users
                userList.value = u
            }

            override fun onFailure(call: Call<UsersResponse>, t: Throwable) {
            }

        })
    }

    fun SignUp(email: String, password: String, fullName: String, phoneNumber: String) {
        udao.uye_ol(email, password, fullName, phoneNumber)
            .enqueue(object : Callback<CRUDResponse> {
                override fun onResponse(
                    call: Call<CRUDResponse>,
                    response: Response<CRUDResponse>
                ) {
                    Log.e("response", response.body()!!.success.toString())
                    Log.e("mesaj", response.body()!!.message)
                }

                override fun onFailure(call: Call<CRUDResponse>, t: Throwable) {
                }

            })
    }


}