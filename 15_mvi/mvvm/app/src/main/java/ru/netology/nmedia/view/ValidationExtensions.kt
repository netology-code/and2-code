package ru.netology.nmedia.view

import android.content.Context
import ru.netology.nmedia.R
import ru.netology.nmedia.domain.Empty
import ru.netology.nmedia.domain.LoginError
import ru.netology.nmedia.domain.PasswordError

fun LoginError?.toString(context: Context): String? = when (this) {
    Empty -> context.getString(R.string.login_empty_error)
    null -> null
}

fun PasswordError?.toString(context: Context): String? = when (this) {
    Empty -> context.getString(R.string.password_empty_error)
    PasswordError.TooShort -> context.getString(R.string.password_too_short_error)
    null -> null
}
