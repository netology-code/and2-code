package ru.netology.nmedia.viewmodel

sealed interface LoginIntent {
    data class SetLogin(val value: String) : LoginIntent
    data class SetPassword(val value: String) : LoginIntent
    object Login : LoginIntent
}
