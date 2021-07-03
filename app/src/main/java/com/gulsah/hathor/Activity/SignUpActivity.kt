package com.gulsah.hathor.Activity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.gulsah.hathor.LoginViewModel
import com.gulsah.hathor.R
import com.gulsah.hathor.databinding.ActivityLoginBinding
import com.gulsah.hathor.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val binding: ActivityLoginBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_sign_up)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel






    }


}

