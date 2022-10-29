package com.es.k_note_api.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.es.k_note_api.data.model.note.NoteRequest
import com.es.k_note_api.data.model.note.NoteResponse
import com.es.k_note_api.models.UserRequest
import com.es.k_note_api.models.UserResponse
import com.es.k_note_api.repo.NoteRepo
import com.es.k_note_api.repo.UserRepo
import com.es.k_note_api.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(private val noteRepo: NoteRepo) : ViewModel() {


    val userResponseLiveData: LiveData<NetworkResult<List<NoteResponse>>>
        get() = noteRepo.noteResponseLiveData


    fun getNotes() {

        viewModelScope.launch {

            noteRepo.getNote()

        }


    }


    fun createNote(noteRequest: NoteRequest) {

        viewModelScope.launch {

            noteRepo.createNote(noteRequest)

        }


    }


}