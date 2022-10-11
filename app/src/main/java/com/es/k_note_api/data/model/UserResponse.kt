package com.es.k_note_api.models

data class UserResponse(
    val token: String,
    val user: User
)