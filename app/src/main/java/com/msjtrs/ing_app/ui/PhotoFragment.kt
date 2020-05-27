package com.msjtrs.ing_app.ui


import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.msjtrs.ing_app.adapters.CommentAdapter
import com.msjtrs.ing_app.adapters.PhotoAdapter
import com.msjtrs.ing_app.adapters.PostAdapter
import com.msjtrs.ing_app.databinding.CommentFragmentBinding
import com.msjtrs.ing_app.databinding.PhotoFragmentBinding

class PhotoFragment : Fragment() {

    private val viewModel: PhotoViewModel by lazy {
        ViewModelProviders.of(this).get(PhotoViewModel::class.java)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val application = requireNotNull(activity).application
        val binding = PhotoFragmentBinding.inflate(inflater)

        binding.setLifecycleOwner(this)

        val userProperty = PhotoFragmentArgs.fromBundle(arguments!!).selectedProperty

        val viewModelFactory = PhotoViewModelFactory(userProperty, application)

        binding.viewModel = ViewModelProviders.of(this, viewModelFactory).get(PhotoViewModel::class.java)

        binding.photosList.adapter = PhotoAdapter()


        return binding.root
    }

}