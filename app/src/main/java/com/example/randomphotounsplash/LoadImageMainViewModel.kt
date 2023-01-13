package com.example.randomphotounsplash

import android.provider.ContactsContract.Contacts.Photo
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoadImageMainViewModel : ViewModel() {
    val liveData = MutableLiveData<ArrayList<Photos>>(ArrayList())
    val liveProgressBar = MutableLiveData<Boolean>(false)


    fun nextImageLoad() {
        liveProgressBar.value = true
        retrofitVar.getNextImage().enqueue(object : Callback<Photos> {
            override fun onResponse(call: Call<Photos>, response: Response<Photos>) {
                Log.d("TAG123", "inside response")
                var responseData = response.body()
                var currentData = liveData.value

                if (responseData != null && currentData != null) {
                    liveData.value?.add(responseData)
                    liveData.value = liveData.value
                    liveProgressBar.value = false
                }
            }

            override fun onFailure(call: Call<Photos>, t: Throwable) {
                t.message?.let { Log.d("TAG123", it) }
//                Toast.makeText(requireActivity(), t.message, Toast.LENGTH_LONG).show()
            }
        })
    }

}