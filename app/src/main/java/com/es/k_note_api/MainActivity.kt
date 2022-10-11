package com.es.k_note_api

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.es.k_note_api.models.UserRequest
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


        val btn = findViewById<Button>(R.id.registerBtn);

        btn.setOnClickListener {
            val userRequest = UserRequest("masum1@gmail.com", "12345678", "Masum")

            authViewModel.registerUser(userRequest)

        }


    }
}