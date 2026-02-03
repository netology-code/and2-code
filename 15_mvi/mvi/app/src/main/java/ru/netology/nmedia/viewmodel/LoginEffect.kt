package ru.netology.nmedia.viewmodel

sealed interface LoginEffect {
    object Success: LoginEffect
}
