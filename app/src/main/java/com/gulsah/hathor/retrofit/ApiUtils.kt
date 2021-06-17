package com.gulsah.hathor.retrofit

class ApiUtils {
    companion object{
        val BASE_URL = "http://upschool.canerture.com/"
        fun getProductsDaoInterface():ProductsDaoInterface{
            return RetrofitClient.getClient(BASE_URL).create(ProductsDaoInterface::class.java)
        }

        fun getUsersDaoInterface():UsersDaoInterface{
            return RetrofitClient.getClient(BASE_URL).create(UsersDaoInterface::class.java)
        }
    }

}