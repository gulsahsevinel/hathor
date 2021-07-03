package com.gulsah.hathor

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gulsah.hathor.Validation.EmailValidator
import com.gulsah.hathor.Validation.PasswordValidator
import com.gulsah.hathor.entity.Users
import com.gulsah.hathor.repo.LoginRepo
import com.gulsah.hathor.repo.ProductsRepo
import kotlin.time.measureTimedValue


class LoginViewModel : ViewModel() {

    val emailLiveData = MutableLiveData<String>()
    val passwordLivaData = MutableLiveData<String>()

    var user = MutableLiveData<List<Users>>()

    val ldaor = LoginRepo()

    val emailError = MutableLiveData<String>()
    val passwordError = MutableLiveData<String>()

    val emailValidator = EmailValidator()
    val passwordValidator = PasswordValidator()

    init {
        userLogin()
        user = ldaor.getUser()
    }

    fun userLogin() {
        ldaor.SignIn(emailLiveData.value.toString(), passwordLivaData.value.toString())
        validation()
        ldaor.getUser()
    }

    fun validation() {
        emailError.value = emailValidator.validate(emailLiveData.value)
        passwordError.value = passwordValidator.validate(passwordLivaData.value)
    }

    fun kayit(email: String, password: String, fullName: String, phoneNumber: String) {
        ldaor.SignUp(email, password, fullName, phoneNumber)
    }


}