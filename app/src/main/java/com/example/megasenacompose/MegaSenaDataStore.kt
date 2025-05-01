package com.example.megasenacompose

import android.content.Context
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.first

val Context.dataStore by preferencesDataStore(name = "mega_sena_prefs")

object MegaSenaPrefs{
    private val LAST_RESULT_KEY = stringPreferencesKey("lastResult")

    suspend fun saveLastResult(context: Context, result: String){

        context.dataStore.edit { prefs ->
            prefs[LAST_RESULT_KEY] = result
        }
    }

    suspend fun getLastResult(context: Context): String {
        val prefs = context.dataStore.data.first()
        return ("Última aposta: " + prefs[LAST_RESULT_KEY]) ?: ""
    }

}