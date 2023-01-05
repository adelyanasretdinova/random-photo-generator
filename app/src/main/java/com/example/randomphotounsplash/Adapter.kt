package com.example.randomphotounsplash

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.w3c.dom.Text
import java.util.Objects


class Adapter(private var itemsList: ArrayList<Photos>) :
    RecyclerView.Adapter<Adapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView

        init {
            imageView = view.findViewById(R.id.imageView)
        }

        val textDescr: TextView

        init {
            textDescr = view.findViewById(R.id.textDescr)
        }

        val likes: TextView

        init {
            likes = view.findViewById(R.id.likes)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.recycler_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide
            .with(holder.itemView.context)
            .load(itemsList[position].urls.regular)
            .into(holder.imageView)
        holder.textDescr.text = itemsList[position].description ?: itemsList[position].alt_description
        holder.likes.text = itemsList[position].likes.toString()
    }

    override fun getItemCount(): Int {
        return itemsList.size
    }
}