package com.es.k_note_api

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.es.k_note_api.api.NoteApi
import com.es.k_note_api.data.model.note.NoteRequest
import com.es.k_note_api.models.UserRequest
import com.es.k_note_api.utils.Constant.TAG
import com.es.k_note_api.utils.NetworkResult
import com.es.k_note_api.utils.TokenManager
import com.es.k_note_api.viewmodel.AuthViewModel
import com.es.k_note_api.viewmodel.NoteViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var tokenManager: TokenManager

    // private val authViewModel by viewModels<AuthViewModel>()
    lateinit var authViewModel: AuthViewModel
    lateinit var noteViewModel: NoteViewModel

    @Inject
    lateinit var noteApi: NoteApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        authViewModel = ViewModelProvider(this)[AuthViewModel::class.java]
        noteViewModel = ViewModelProvider(this)[NoteViewModel::class.java]


        val reg_btn = findViewById<Button>(R.id.registerBtn)
        val login_btn = findViewById<Button>(R.id.loginBtn)
        val prgress = findViewById<ProgressBar>(R.id.progressBar)

        reg_btn.setOnClickListener {
            val userRequest = UserRequest("masum37@gmail.com", "12345678", "Masum")

            authViewModel.registerUser(userRequest)
            prgress.visibility = View.VISIBLE


        }

        login_btn.setOnClickListener {
            //   val userRequest = UserRequest("masum@gmail.com", "12345678", "Masum")

            // authViewModel.registerUser(userRequest)
            // prgress.visibility = View.VISIBLE


        }
        noteViewModel.userResponseLiveData.observe(this) { state ->

            when (state) {
                is NetworkResult.Error -> {

                    Log.i(TAG, "onCreate Note: ${state.message} ")

                }
                is NetworkResult.Loading -> {
                    Log.i(TAG, "Loading Note..... ")
                }
                is NetworkResult.Success -> {
                    prgress.visibility = View.GONE
                    Log.i(TAG, "Note : ${state.data}")


                }
            }


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
                    tokenManager.saveToken(state.data!!.token)
                    Log.i(TAG, "${state.data}")
                    Log.i(TAG, "token get : ${tokenManager.getToken()}")

                    for (i in 1..10) {
                        noteViewModel.createNote(NoteRequest(" Desc $i ", " ttitle $i"))
                    }
                    noteViewModel.getNotes()


                }
            }


        }


    }


}