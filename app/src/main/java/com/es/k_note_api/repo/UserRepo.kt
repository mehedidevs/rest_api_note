package com.es.k_note_api.repo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.es.k_note_api.api.UserApi
import com.es.k_note_api.models.UserRequest
import com.es.k_note_api.models.UserResponse
import com.es.k_note_api.utils.Constant.TAG
import com.es.k_note_api.utils.NetworkResult
import javax.inject.Inject

class UserRepo @Inject constructor(private val userApi: UserApi) {

    private val _useResponseLiveData = MutableLiveData<NetworkResult<UserResponse>>()
    val useResponseLiveData: LiveData<NetworkResult<UserResponse>>
        get() = _useResponseLiveData


    suspend fun registerUser(userRequest: UserRequest) {
        _useResponseLiveData.postValue(NetworkResult.Loading())

        val response = userApi.signup(userRequest)
        if (response.isSuccessful && response.body() != null) {
            _useResponseLiveData.postValue(NetworkResult.Success(response.body()!!))

        } else if (response.errorBody() != null) {
            _useResponseLiveData.postValue(NetworkResult.Error("Something Went Wrong"))

        } else {
            _useResponseLiveData.postValue(NetworkResult.Error("Something Went Wrong"))

        }





        Log.i(TAG, "registerUser:${response.toString()} ")


    }

    suspend fun loginUser(userRequest: UserRequest) {
        val response = userApi.signin(userRequest)

        Log.i(TAG, "loginUser : ${response.body().toString()}")
    }


}