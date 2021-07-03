package com.gulsah.hathor

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout

@BindingAdapter("android:error")
fun setError(textInputLayout: TextInputLayout, error: String?) {
    textInputLayout.error = error
    textInputLayout.isErrorEnabled = false != null
}

@BindingAdapter("android:setPrice")
fun setPrice(textview: TextView, price: String) {
    textview.text = "₺ $price "
}