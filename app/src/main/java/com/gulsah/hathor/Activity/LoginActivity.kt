package com.gulsah.hathor.Activity

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.widget.doOnTextChanged
import androidx.databinding.DataBindingUtil
import com.gulsah.hathor.LoginViewModel
import com.gulsah.hathor.R
import com.gulsah.hathor.databinding.ActivityLoginBinding
import com.gulsah.hathor.fragment.CustomDialogFragment


class LoginActivity : AppCompatActivity() {

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityLoginBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        binding.emailEditText.doOnTextChanged { text, start, before, count ->
            viewModel.validation()
        }
        binding.passwordEditText.doOnTextChanged { text, start, before, count ->
            viewModel.validation()
        }

        binding.forgottenPassword.setOnClickListener {
            forgotPasswordAlertDialog()
        }

        viewModel.user.observe(this) {
            if (it[0].user_value == 1) {

                //shared prefences
                val sharedPreferences =
                    getSharedPreferences("com.gulsah.hathor", Context.MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                editor.apply {
                    putString("STRING_NAME", it[0].user_fullName)
                    putString("STRING_MAIL", it[0].user_mail)
                    putString("STRING_PHONE", it[0].user_phoneNumber)
                }.apply()

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)

            } else if (it[0].user_value == 0) {
                //Toast.makeText(this, "login failed", Toast.LENGTH_SHORT).show()
            }
        }

        binding.signUpTextView.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

    }

    fun forgotPasswordAlertDialog() {
        val mDialogView =
            LayoutInflater.from(this).inflate(R.layout.fragment_custom_dialog, null)
        val button: Button = mDialogView.findViewById(R.id.buttonSendMail)

        val mBuilder = AlertDialog.Builder(this).setView(mDialogView)
        val mAlertDialog = mBuilder.show()
        mAlertDialog.window?.setLayout(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        button.setOnClickListener {
            mAlertDialog.dismiss()
            Toast.makeText(
                this,
                "An email has been sent. Please click the link when you get it.",
                Toast.LENGTH_SHORT
            ).show()
        }
        mAlertDialog.window?.setBackgroundDrawableResource(R.drawable.dialog_background)
    }


}