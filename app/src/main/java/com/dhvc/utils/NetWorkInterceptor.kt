package com.dhvc.utils

import okhttp3.Interceptor
import okhttp3.Response

class NetWorkInterceptor : Interceptor {
//    var access_token: String = "c45fb11c-dc9f-4692-8a5f-2f46d3aeb38d"

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("Content-Type", "application/json")
            .addHeader("Authorization", "Bearer" + "c45fb11c-dc9f-4692-8a5f-2f46d3aeb38d")
            .build()
        return chain.proceed(request)
    }

}