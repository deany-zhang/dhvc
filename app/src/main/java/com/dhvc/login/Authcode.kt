package com.dhvc.login

data class Authcode(
    val `data`: DataX,
    val message: String,
    val status: Int

) {
    override fun toString(): String {
        return "Authcode(`data`=$`data`, message='$message', status=$status)"
    }
}

data class DataX(
    val capToken: String,
    val img: String

) {
    override fun toString(): String {
        return "DataX(capToken='$capToken', img='$img')"
    }
}