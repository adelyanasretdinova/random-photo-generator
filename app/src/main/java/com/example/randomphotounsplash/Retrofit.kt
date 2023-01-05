package com.example.randomphotounsplash

import com.example.randomphotounsplash.Retrofit.retrofit
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Retrofit {

    private const val BASE_URL = "https://api.unsplash.com/"


    val retrofit= Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()


}

val retrofitVar = retrofit.create(RetrofitAPI::class.java)
