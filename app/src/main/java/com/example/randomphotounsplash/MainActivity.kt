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
    val itemsList: ArrayList<ArrayList<String>> = ArrayList()
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
                val oneItemList : ArrayList<String>  = ArrayList()
                var responseImage = response.body()?.urls?.regular
                var responseLikes = response.body()?.likes.toString()
                var responseDescription = response.body()?.description
                var responseAltDescription = response.body()?.alt_description
                if (responseImage != null) {
                    oneItemList.add(responseImage)
                }
                    if (responseDescription != null) {
                        oneItemList.add(responseDescription)
                    } else
                            oneItemList.add(responseAltDescription!!)
                    oneItemList.add(responseLikes)
                    itemsList.add(oneItemList)
                    customAdapter.notifyDataSetChanged()
                }

            override fun onFailure(call: Call<Photos>, t: Throwable) {
                Toast.makeText(this@MainActivity, "There is an error", Toast.LENGTH_SHORT).show()
            }
        })
    }
}

