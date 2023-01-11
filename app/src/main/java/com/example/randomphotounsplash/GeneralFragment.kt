package com.example.randomphotounsplash


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class GeneralFragment : Fragment() {

    private lateinit var buttonNext: Button
    private val customAdapter: Adapter = Adapter()
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_general, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonNext = view.findViewById<Button>(R.id.buttonNext)

        buttonNext.setOnClickListener {
            nextImageLoad()
        }



        recyclerView = view.findViewById<RecyclerView>(R.id.imagesManyView)
        recyclerView.adapter = customAdapter


        customAdapter.onItemClick = { Photos ->
            val bundle = Bundle()
            bundle.putString("url", Photos.urls.regular)
            findNavController().navigate(R.id.action_generalFragment_to_fullScreenPhotoFragment,bundle)
        }
    }


    private fun nextImageLoad() {
        retrofitVar.getNextImage().enqueue(object : Callback<Photos> {
            override fun onResponse(call: Call<Photos>, response: Response<Photos>) {
                Log.d("TAG123", "inside response")
                val responseData = response.body()
                if (responseData != null) {
                    customAdapter.itemsList.add(responseData)
                    customAdapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<Photos>, t: Throwable) {
//                Log.d("TAG123","inside onfaliure")
                Toast.makeText(requireActivity(), t.message, Toast.LENGTH_LONG).show()
            }
        })
    }

}