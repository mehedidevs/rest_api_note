package com.es.k_note_api.utils

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class TokenManager @Inject constructor(@ApplicationContext context: Context) {

    private var prefs = context.getSharedPreferences("token_prefs", Context.MODE_PRIVATE)


    fun saveToken(token: String) {
        val editor = prefs.edit()
        editor.putString("token", token)
        editor.apply()

    }

    fun getToken(): String? {
        return prefs.getString("token", "")
    }


}