package com.dhvc.login

data class loginBean<T>(
    val status: String,
    val user_info: T

)

data class UserInfo(
    val token: String
)