package com.example.randomphotounsplash

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

class MainActivity : AppCompatActivity() {
    private lateinit var buttonNext: Button
    val itemsList: ArrayList<Photos> = ArrayList()
    private lateinit var customAdapter: Adapter
    private lateinit var recyclerView: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        buttonNext = findViewById(R.id.buttonNext)

        buttonNext.setOnClickListener {
            nextImageLoad()
        }

        recyclerView = findViewById(R.id.imagesManyView)
        customAdapter = Adapter(itemsList)
        recyclerView.adapter = customAdapter

    }

    private fun nextImageLoad() {
       retrofitVar.getNextImage().enqueue(object : Callback<Photos> {
            override fun onResponse(call: Call<Photos>, response: Response<Photos>) {

                var responseData = response.body()
                if (responseData != null) {
                    itemsList.add(Photos(responseData.urls, responseData.likes, responseData.description,responseData.alt_description))
                }

                customAdapter.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<Photos>, t: Throwable) {
                Toast.makeText(this@MainActivity, "There is an error", Toast.LENGTH_SHORT).show()
            }
        })
    }
}

