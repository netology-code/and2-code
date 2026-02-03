package ru.netology.nmedia.view

import ru.netology.nmedia.domain.LoginError
import ru.netology.nmedia.domain.PasswordError

interface LoginView {
    fun showLoginError(loginError: LoginError?)
    fun showPasswordError(passwordError: PasswordError?)
    fun showSuccess()
}
