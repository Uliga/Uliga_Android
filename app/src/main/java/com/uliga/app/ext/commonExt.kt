package com.uliga.app.ext

import android.util.Patterns
import java.util.regex.Pattern

fun isNickNameTypeValid(nickname: String): Boolean {
    return Pattern.matches("^[0-9a-zA-Z가-힣]*$", nickname)
}

fun isEmailValid(email: CharSequence): Boolean {
    return Patterns.EMAIL_ADDRESS.matcher(email).matches()
}