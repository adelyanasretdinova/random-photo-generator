package com.example.randomphotounsplash

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

public interface RetrofitAPI {
    @GET("photos/random/")
    @Headers("Authorization: Client-ID IgdgRLAFRfD9zGw8Ge8WN0qqxlCgKBl5VpT75PD_mKM")
    fun getNextImage(): Call<Photos>
}