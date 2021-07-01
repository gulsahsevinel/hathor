package com.gulsah.hathor

import android.content.Intent
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gulsah.hathor.Validation.EmailValidator
import com.gulsah.hathor.Validation.PasswordValidator
import com.gulsah.hathor.repo.LoginRepo


class LoginViewModel : ViewModel() {
    val emailLiveData = MutableLiveData<String>()
    val passwordLivaData = MutableLiveData<String>()

    val emailError = MutableLiveData<String>()
    val passwordError = MutableLiveData<String>()

    private val emailValidator = EmailValidator()
    private val passwordValidator = PasswordValidator()

    val loginRepo = LoginRepo()

    fun onSignInButtonClick() {
        emailError.value = emailValidator.validate(emailLiveData.value)
        passwordError.value = passwordValidator.validate(passwordLivaData.value)
        loginRepo.SignIn(emailLiveData.value.toString(),passwordLivaData.value.toString())
     }




}