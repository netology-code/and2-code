package ru.netology.nmedia.presenter

import ru.netology.nmedia.domain.LoginError
import ru.netology.nmedia.domain.PasswordError
import ru.netology.nmedia.domain.validateLogin
import ru.netology.nmedia.domain.validatePassword
import ru.netology.nmedia.view.LoginView

class LoginPresenter {
    private var view: LoginView? = null
    private var loginError: LoginError? = null
    private var passwordError: PasswordError? = null

    init {
        setLogin(null)
        setPassword(null)
    }

    fun setView(view: LoginView?) {
        this.view = view
    }

    fun setLogin(login: String?) {
        view?.showLoginError(null)
        loginError = validateLogin(login)
    }

    fun setPassword(password: String?) {
        view?.showPasswordError(null)
        passwordError = validatePassword(password)
    }

    fun login() {
        view?.showPasswordError(passwordError)
        view?.showLoginError(loginError)

        if (loginError == null && passwordError == null) {
            view?.showSuccess()
        }
    }
}
