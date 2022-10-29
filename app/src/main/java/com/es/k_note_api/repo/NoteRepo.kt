package com.es.k_note_api.repo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.es.k_note_api.api.NoteApi
import com.es.k_note_api.api.UserApi
import com.es.k_note_api.data.model.note.NoteRequest
import com.es.k_note_api.data.model.note.NoteResponse
import com.es.k_note_api.models.UserRequest
import com.es.k_note_api.models.UserResponse
import com.es.k_note_api.utils.Constant.TAG
import com.es.k_note_api.utils.NetworkResult
import org.json.JSONObject
import javax.inject.Inject

class NoteRepo @Inject constructor(private val noteApi: NoteApi) {

    private val _noteResponseLiveData = MutableLiveData<NetworkResult<List<NoteResponse>>>()
    val noteResponseLiveData: LiveData<NetworkResult<List<NoteResponse>>>
        get() = _noteResponseLiveData


    suspend fun getNote() {
        _noteResponseLiveData.postValue(NetworkResult.Loading())

        val response = noteApi.getNotes()
        if (response.isSuccessful && response.body() != null) {
            _noteResponseLiveData.postValue(NetworkResult.Success(response.body()!!))

        } else if (response.errorBody() != null) {
            val errorObj = JSONObject(response.errorBody()!!.charStream().readText())

            _noteResponseLiveData.postValue(NetworkResult.Error(errorObj.getString("message")))


        } else {
            _noteResponseLiveData.postValue(NetworkResult.Error("Something Went Wrong"))
        }

        Log.i(TAG, "registerUser:${response.toString()} ")


    }

    suspend fun createNote(noteRequest: NoteRequest) {
        val response = noteApi.createNote(noteRequest)

        Log.i(TAG, "loginUser : ${response.body().toString()}")
    }


}