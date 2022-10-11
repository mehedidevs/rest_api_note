package com.es.k_note_api.repo

import android.util.Log
import com.es.k_note_api.api.UserApi
import com.es.k_note_api.models.UserRequest
import com.es.k_note_api.models.UserResponse
import com.es.k_note_api.utils.Constant.TAG
import javax.inject.Inject

class UserRepo @Inject constructor(private val userApi: UserApi) {


    suspend fun registerUser(userRequest: UserRequest) {
        val response:UserResponse = userApi.signup(userRequest).body()!!
        Log.i(TAG, "registerUser:${response.toString()} ")




    }

    suspend fun loginUser(userRequest: UserRequest) {
        val response = userApi.signin(userRequest)

        Log.i(TAG, "loginUser : ${response.body().toString()}")
    }


}