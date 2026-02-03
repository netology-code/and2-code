package ru.netology.nmedia.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import ru.netology.nmedia.domain.validateLogin
import ru.netology.nmedia.domain.validatePassword

class LoginViewModel : ViewModel() {
    private val _state = MutableStateFlow(LoginState())
    private val _effects = Channel<LoginEffect>(Channel.BUFFERED)

    val state = _state.asStateFlow()
    val effects = _effects.receiveAsFlow()

    fun sendIntent(intent: LoginIntent) {
        when (intent) {
            LoginIntent.Login -> reduce {
                val loginError = validateLogin(login)
                val passwordError = validatePassword(password)

                if (loginError == null && passwordError == null) {
                    _effects.trySend(LoginEffect.Success)
                }

                copy(
                    loginError = loginError,
                    passwordError = passwordError,
                )
            }

            is LoginIntent.SetLogin -> reduce {
                copy(login = intent.value, loginError = null)
            }

            is LoginIntent.SetPassword -> reduce {
                copy(password = intent.value, passwordError = null)
            }
        }
    }

    private inline fun reduce(mutation: LoginState.() -> LoginState) {
        _state.update { mutation(it) }
    }
}
