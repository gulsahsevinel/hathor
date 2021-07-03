package com.gulsah.hathor

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gulsah.hathor.repo.SignUpRepo

class SignUpViewModel : ViewModel() {

    val first_name = MutableLiveData<String>()
    val last_name = MutableLiveData<String>()
    val phone_number = MutableLiveData<String>()
    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()

    var success = MutableLiveData<Int>()

    val sdaor = SignUpRepo()

    init {
        success = sdaor.getSuccessValue()
    }

    val fullName = first_name.value.toString() + " " + last_name.value.toString()
    fun userSignIn() {
        sdaor.SignUp(
            email.value.toString(),
            password.value.toString(),
            fullName,
            phone_number.value.toString()
        )
    }

}