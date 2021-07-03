package com.gulsah.hathor.Activity


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.gulsah.hathor.R
import com.gulsah.hathor.SignUpViewModel
import com.gulsah.hathor.databinding.ActivitySignUpBinding


class SignUpActivity : AppCompatActivity() {

    private val viewModel: SignUpViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val binding: ActivitySignUpBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_sign_up)
        binding.lifecycleOwner = this
        binding.viewModelSignUp = viewModel

        viewModel.success.observe(this) {
            if (it == 1) {
                Toast.makeText(this, "başarılı", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "başarısız", Toast.LENGTH_SHORT).show()
            }
        }


    }


}

