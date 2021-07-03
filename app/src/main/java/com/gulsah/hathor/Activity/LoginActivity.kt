package com.gulsah.hathor.Activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import com.google.android.material.snackbar.Snackbar
import com.gulsah.hathor.LoginViewModel
import com.gulsah.hathor.R
import com.gulsah.hathor.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityLoginBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        binding.forgottenPassword.setOnClickListener {
            val mDialogView =
                LayoutInflater.from(this).inflate(R.layout.fragment_custom_dialog, null)
            val mBuilder = AlertDialog.Builder(this).setView(mDialogView)
            val mAlertDialog = mBuilder.show()
            mAlertDialog.window?.setBackgroundDrawableResource(R.drawable.dialog_background)
        }

        binding.signUpTextView.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        viewModel.user.observe(this) {
            if (it[0].user_value == 1) {

                //shared prefences
                val sharedPreferences = getSharedPreferences("com.gulsah.hathor", Context.MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                editor.apply {
                    putInt("INT_USER_ID",it[0].user_id)
                    putString("STRING_NAME", it[0].user_fullName)
                    putString("STRING_MAIL", it[0].user_mail)
                    putString("STRING_PHONE", it[0].user_phoneNumber)

                }.apply()

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)

            } else if (it[0].user_value == 0) {
                Toast.makeText(this, "null", Toast.LENGTH_SHORT).show()
            }
        }

        binding.signInButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }


    }


}