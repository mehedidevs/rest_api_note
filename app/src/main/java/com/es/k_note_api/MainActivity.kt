package com.es.k_note_api

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.es.k_note_api.models.UserRequest
import com.es.k_note_api.utils.Constant.TAG
import com.es.k_note_api.utils.NetworkResult
import com.es.k_note_api.viewmodel.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    // private val authViewModel by viewModels<AuthViewModel>()
    lateinit var authViewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        authViewModel = ViewModelProvider(this)[AuthViewModel::class.java]


        val btn = findViewById<Button>(R.id.registerBtn)
        val prgress = findViewById<ProgressBar>(R.id.progressBar)

        btn.setOnClickListener {
            val userRequest = UserRequest("masum2@gmail.com", "12345678", "Masum")

            authViewModel.registerUser(userRequest)
            prgress.visibility = View.VISIBLE


        }


        authViewModel.userResponseLiveData.observe(this) { state ->
            when (state) {
                is NetworkResult.Error -> {

                    Log.i(TAG, "onCreate: ${state.message} ")

                }
                is NetworkResult.Loading -> {
                    Log.i(TAG, "Loading ")
                }
                is NetworkResult.Success -> {
                    prgress.visibility = View.GONE
                    //token
                    Log.i(TAG, "Data Loaded")

                }
            }


        }


    }


}