package com.gulsah.hathor.Validation

class EmailValidator {
    fun validate(email: String?) =
        if (email == null) "Email cannot be empty"
        else if (email.isBlank()) "Email cannot be empty"
        else if (email.length < 5) "Email cannot be this short"
        else if (!email.contains("@")
            || !email.contains(".")
        ) "Invalid email"
        else null
}