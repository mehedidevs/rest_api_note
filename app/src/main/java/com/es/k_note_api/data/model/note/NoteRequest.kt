package com.es.k_note_api.data.model.note

import com.google.gson.annotations.SerializedName
data class NoteRequest(

    @field:SerializedName("description")
    val description: String? = null,

    @field:SerializedName("title")
    val title: String? = null
)
