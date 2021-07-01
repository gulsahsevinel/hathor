package com.gulsah.hathor.repo

import android.util.Log
import com.gulsah.hathor.entity.UsersResponse
import com.gulsah.hathor.retrofit.ApiUtils
import com.gulsah.hathor.retrofit.UsersDaoInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginRepo {

    private val udao: UsersDaoInterface = ApiUtils.getUsersDaoInterface()


    fun SignIn(email: String, password: String) {
        udao.giris_yap(email, password).enqueue(object :
            Callback<UsersResponse> {
            override fun onResponse(call: Call<UsersResponse>, response: Response<UsersResponse>) {
                val u = response.body()!!.users
                //Log.e("deger", "${response.body()!!.success}")
                //Log.e("mail", email)
                for (i in u) {
                    //Log.e("deger", "${response.body()!!.users}")
                    //Log.e("deger", "${i.user_fullName}")

                }
            }
            override fun onFailure(call: Call<UsersResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }


}