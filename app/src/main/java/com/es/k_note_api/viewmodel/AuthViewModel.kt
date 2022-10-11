package com.es.k_note_api.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.es.k_note_api.models.UserRequest
import com.es.k_note_api.models.UserResponse
import com.es.k_note_api.repo.UserRepo
import com.es.k_note_api.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val userRepo: UserRepo) : ViewModel() {


    val userResponseLiveData: LiveData<NetworkResult<UserResponse>>
        get() = userRepo.useResponseLiveData


    fun registerUser(userRequest: UserRequest) {

        viewModelScope.launch {

            userRepo.registerUser(userRequest)

        }


    }

    fun loginUser(userRequest: UserRequest) {
        viewModelScope.launch {

            userRepo.loginUser(userRequest)

        }
    }

}