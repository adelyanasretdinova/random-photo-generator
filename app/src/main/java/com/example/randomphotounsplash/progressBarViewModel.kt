//package com.example.randomphotounsplash
//
//import android.view.View
//import androidx.lifecycle.ViewModel
//import androidx.recyclerview.widget.RecyclerView.ViewHolder
//import kotlin.properties.Delegates
//
//class progressBarViewModel : ViewModel() {
//    lateinit var progressBar: View
//    var visibleBar = false
//
//    fun barVisible():Int {
//       visibleBar = true
//        if (visibleBar == false) {
//            progressBar.visibility  = View.GONE
//        } else  progressBar.visibility  = View.VISIBLE
//        return progressBar.visibility
//    }
//}