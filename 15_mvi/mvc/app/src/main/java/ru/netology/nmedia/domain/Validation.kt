package ru.netology.nmedia.domain

sealed interface LoginError

sealed interface PasswordError {
    object TooShort : PasswordError
}

object Empty : PasswordError, LoginError

fun validateLogin(login: CharSequence?): LoginError? =
    if (login.isNullOrBlank()) {
        Empty
    } else {
        null
    }

fun validatePassword(password: CharSequence?): PasswordError? =
    when {
        password.isNullOrBlank() -> Empty
        password.length < 6 -> PasswordError.TooShort
        else -> null
    }
