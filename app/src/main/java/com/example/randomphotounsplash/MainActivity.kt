package com.example.randomphotounsplash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var buttonNext: Button
    val itemsList: ArrayList<String> = ArrayList()
    private lateinit var customAdapter: Adapter
    private lateinit var recyclerView: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        itemsList.add("https://images.unsplash.com/photo-1672159874961-5d9da5e1830b?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwzOTUzMDd8MHwxfHJhbmRvbXx8fHx8fHx8fDE2NzI4NDc1OTc&ixlib=rb-4.0.3&q=80&w=1080")

        buttonNext = findViewById(R.id.buttonNext)

        buttonNext.setOnClickListener {
            nextImageLoad()
        }

        recyclerView = findViewById(R.id.imagesManyView)
        customAdapter = Adapter(itemsList)
        recyclerView.adapter = customAdapter
        Log.d("TAG123", "itemsList ${itemsList[0]}")

    }

    private fun nextImageLoad() {
        Log.d("TAG123", "on FUNCTION")
       retrofitVar.getNextImage().enqueue(object : Callback<Photos> {
            override fun onResponse(call: Call<Photos>, response: Response<Photos>) {
                Log.d("TAG123", "on response")
                var responseImage = response.body()?.urls?.regular
                Log.d("TAG123", "response : $responseImage")
                if (responseImage != null) {
                    itemsList.add(responseImage)
                    customAdapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<Photos>, t: Throwable) {
                Toast.makeText(this@MainActivity, "There is an error", Toast.LENGTH_SHORT).show()
                Log.d("TAG123", "on faliure ${t.message}")
            }
        })
    }
}

