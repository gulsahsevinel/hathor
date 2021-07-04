package com.gulsah.hathor.Validation

class PasswordValidator {
    fun validate(password: String?) = when {
        password == null -> "Password cannot be empty"
        password.isBlank() -> "Password cannot be empty"
        else -> null
    }


}
