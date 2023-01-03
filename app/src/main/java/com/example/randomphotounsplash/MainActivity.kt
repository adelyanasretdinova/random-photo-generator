package com.example.randomphotounsplash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var image: ImageView
    private lateinit var buttonNext: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        image = findViewById(R.id.imageView)
        buttonNext = findViewById(R.id.buttonNext)

        buttonNext.setOnClickListener {
            nextImageLoad()
        }

    }
    private fun nextImageLoad() {
        retrofitVar.getNextImage().enqueue(object: Callback<Photos> {
            override fun onResponse(call: Call<Photos>, response: Response<Photos>) {
                var response = response.body()?.urls?.regular
                Glide
                    .with(this@MainActivity)
                    .load(response)
                    .into(image)
            }

            override fun onFailure(call: Call<Photos>, t: Throwable) {
                    Toast.makeText(this@MainActivity, "There is an error", Toast.LENGTH_SHORT).show()
            }

        })
        }

    }
