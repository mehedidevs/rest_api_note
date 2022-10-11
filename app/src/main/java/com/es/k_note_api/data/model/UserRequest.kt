package com.es.k_note_api.models

data class UserRequest(
    val email: String,
    val password: String,
    val username: String
)