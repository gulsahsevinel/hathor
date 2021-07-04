package com.gulsah.hathor

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.*
import com.gulsah.hathor.Validation.EmailValidator
import com.gulsah.hathor.Validation.PasswordValidator
import com.gulsah.hathor.entity.Users
import com.gulsah.hathor.repo.LoginRepo
import com.gulsah.hathor.repo.ProductsRepo
import kotlin.time.measureTimedValue


class LoginViewModel(application: Application) : AndroidViewModel(application) {

    var user = MutableLiveData<List<Users>>()

    val ldaor = LoginRepo()

    val emailError = MutableLiveData<String>()
    val passwordError = MutableLiveData<String>()

    val emailLiveData = MutableLiveData<String>()
    val passwordLivaData = MutableLiveData<String>()


    init {
        userLogin()
        user = ldaor.getUser()
    }

    fun userLogin() {
        ldaor.SignIn(emailLiveData.value.toString(), passwordLivaData.value.toString())
        ldaor.getUser()
    }

    fun validation() {
        emailValidate(emailLiveData.value.toString())
        passwordValidate(passwordLivaData.value.toString())
    }

    fun emailValidate(email: String): Boolean {
        if (email == null) {
            emailError.value = getString(R.string.email_null_error)
            return false
        } else if (email.isBlank()) {
            emailError.value = getString(R.string.email_null_error)
            return false
        } else if (email.length < 5) {
            emailError.value = getString(R.string.email_short_error)
            return false
        } else if (!email.contains("@")
            || !email.contains(".")
        ) {
            emailError.value = getString(R.string.email_invalid_error)
            return false
        }
        emailError.value = ""
        return true
    }

    fun passwordValidate(password: String?): Boolean {
        if (password == null) {
            passwordError.value = getString(R.string.password_null_error)
            return false
        } else if (password.isBlank()) {
            passwordError.value = getString(R.string.password_null_error)
            return false
        }
        passwordError.value = ""
        return true
    }


    private fun getString(id: Int): String {
        return getApplication<Application>().resources.getString(id)
    }

}