package ru.netology.nmedia.viewmodel

import ru.netology.nmedia.domain.LoginError
import ru.netology.nmedia.domain.PasswordError

data class LoginState(
    val login: String = "",
    val password: String = "",
    val loginError: LoginError? = null,
    val passwordError: PasswordError? = null,
)
