package com.es.k_note_api.api

import com.es.k_note_api.utils.TokenManager
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor() : Interceptor {

    @Inject
    lateinit var tokenManager: TokenManager

    override fun intercept(chain: Interceptor.Chain): Response {
        val token = tokenManager.getToken()
        val request = chain.request().newBuilder()

        request.addHeader("Authorization", "Bearer $token")
      //  request.addHeader("Content-Type", "application/json")

        return chain.proceed(request.build())
    }


}