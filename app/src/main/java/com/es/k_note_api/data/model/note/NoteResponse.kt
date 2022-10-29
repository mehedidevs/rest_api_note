package com.es.k_note_api.data.model.note

data class NoteResponse(
    val __v: Int,
    val _id: String,
    val createdAt: String,
    val description: String,
    val title: String,
    val updatedAt: String,
    val userId: String
) {
    override fun toString(): String {
        return "NoteResponse(__v=$__v, _id='$_id', createdAt='$createdAt', description='$description', title='$title', updatedAt='$updatedAt', userId='$userId')"
    }
}