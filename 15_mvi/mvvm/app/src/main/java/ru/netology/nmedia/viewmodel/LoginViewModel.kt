package ru.netology.nmedia.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import ru.netology.nmedia.domain.LoginError
import ru.netology.nmedia.domain.PasswordError
import ru.netology.nmedia.domain.validateLogin
import ru.netology.nmedia.domain.validatePassword

class LoginViewModel : ViewModel() {
    private val _loginError = MutableStateFlow<LoginError?>(null)
    private val _passwordError = MutableStateFlow<PasswordError?>(null)
    private val _loginSuccess = Channel<Unit>(Channel.BUFFERED)
    private var login: String? = null
    private var password: String? = null

    val loginError = _loginError.asStateFlow()
    val passwordError = _passwordError.asStateFlow()
    val loginSuccess = _loginSuccess.receiveAsFlow()

    fun setLogin(login: String?) {
        this.login = login
        _loginError.value = null
    }

    fun setPassword(password: String?) {
        this.password = password
        _passwordError.value = null
    }

    fun login() {
        val loginError = validateLogin(login)
        val passwordError = validatePassword(password)

        _loginError.value = loginError
        _passwordError.value = passwordError

        if (loginError == null && passwordError == null) {
            _loginSuccess.trySend(Unit)
        }
    }
}
