package com.dhvc.utils

import com.dhvc.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
private const val timeoutRead = 30   //In seconds
private const val contentType = "Content-Type"
private const val contentTypeValue = "application/json"
private const val timeoutConnect = 30   //In seconds
open class ServiceGenerator(url :String){
    private val okHttpBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
    private val retrofit: Retrofit

    private var headerInterceptor = Interceptor { chain ->
        val original = chain.request()

        val request = original.newBuilder()
            .header(contentType, contentTypeValue)
            .method(original.method, original.body)
            .build()

        chain.proceed(request)

    }
    init {
//        val okHttpClient: OkHttpClient = OkHttpClient
//            .Builder()
//            .connectTimeout(100, TimeUnit.SECONDS)
//            .readTimeout(100, TimeUnit.SECONDS)
//            .addInterceptor(NetWorkInterceptor())
//            .build()

        okHttpBuilder.addInterceptor(headerInterceptor)
        okHttpBuilder.addInterceptor(logger)
        okHttpBuilder.connectTimeout(timeoutConnect.toLong(), TimeUnit.SECONDS)
        okHttpBuilder.readTimeout(timeoutRead.toLong(), TimeUnit.SECONDS)
        val client = okHttpBuilder.build()

        retrofit = Retrofit.Builder()
            .baseUrl(url)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private val logger: HttpLoggingInterceptor
        get() {
            val loggingInterceptor = HttpLoggingInterceptor()
            if (BuildConfig.DEBUG) {
                loggingInterceptor.apply { level = HttpLoggingInterceptor.Level.BODY }
            }
            return loggingInterceptor
        }

    fun <S> createService(serviceClass: Class<S>): S {
        return retrofit.create(serviceClass)
    }

}