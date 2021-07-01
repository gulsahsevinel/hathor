package com.gulsah.hathor

import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout

@BindingAdapter("android:error")
fun setError(textInputLayout: TextInputLayout, error: String?) {
    textInputLayout.error = error
    textInputLayout.isErrorEnabled = false != null

}