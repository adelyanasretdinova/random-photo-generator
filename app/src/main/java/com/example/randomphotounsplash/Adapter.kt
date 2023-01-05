package com.example.randomphotounsplash

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide


class Adapter(private var itemsList: ArrayList<String>): RecyclerView.Adapter<Adapter.ViewHolder>() {
    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val imageView: ImageView

        init {
            imageView = view.findViewById(R.id.imageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view:View = LayoutInflater.from(parent.context).inflate(R.layout.recycler_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.d("TAG999", "${itemsList[position]}")
                Glide
                    .with(holder.itemView.context)
                    .load(itemsList[position])
                    .into(holder.imageView)
//        holder.imageView.setImageResource(android.R.drawable.arrow_up_float)

    }


    override fun getItemCount(): Int {
        return itemsList.size
    }
}