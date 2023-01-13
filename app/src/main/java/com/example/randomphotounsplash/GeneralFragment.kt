package com.example.randomphotounsplash


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.randomphotounsplash.databinding.FragmentGeneralBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class GeneralFragment : Fragment() {
    private lateinit var viewModel: LoadImageMainViewModel
    private val customAdapter: Adapter = Adapter()
    private lateinit var binding: FragmentGeneralBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[LoadImageMainViewModel::class.java]

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentGeneralBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        binding.progressbar.visibility = View.GONE

        viewModel.liveData.observe(viewLifecycleOwner) {
            customAdapter.itemsList = it
            customAdapter.notifyDataSetChanged()
        }

        viewModel.liveProgressBar.observe(viewLifecycleOwner) {
                binding.progressbar.isVisible = it
        }

        binding.buttonNext.setOnClickListener {
            viewModel.nextImageLoad()
        }

        binding.imagesManyView.adapter = customAdapter
        customAdapter.onItemClick = { Photos ->
            val bundle = Bundle()
            bundle.putString("url", Photos.urls.regular)
            findNavController().navigate(
                R.id.action_generalFragment_to_fullScreenPhotoFragment,
                bundle
            )
        }

        binding.progressbar

    }


}