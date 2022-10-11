package com.es.k_note_api.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.es.k_note_api.models.UserRequest
import com.es.k_note_api.repo.UserRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val userRepo: UserRepo) : ViewModel() {

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