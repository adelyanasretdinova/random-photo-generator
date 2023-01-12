package com.example.randomphotounsplash

import android.content.Context
import android.graphics.Insets.add
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import androidx.navigation.fragment.NavHostFragment

class MainActivity : AppCompatActivity() {

    companion object {
        const val SHARED_PREFERENCE_KEY = "MY_PREFS"
        const val USER_VISITED__KEY = "MY_PREFS"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

val sharePreference = getSharedPreferences(SHARED_PREFERENCE_KEY, Context.MODE_PRIVATE)
val userVisited = sharePreference.getBoolean(USER_VISITED__KEY, false)

        if(!userVisited) {
            Toast.makeText(this, "Hello New Visitor", Toast.LENGTH_LONG).show()
            sharePreference.edit().putBoolean(USER_VISITED__KEY, true).apply()
        }

    }


}

