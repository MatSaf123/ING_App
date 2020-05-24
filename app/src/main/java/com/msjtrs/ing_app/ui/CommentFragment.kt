package com.msjtrs.ing_app.ui


import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.msjtrs.ing_app.adapters.CommentAdapter
import com.msjtrs.ing_app.databinding.CommentFragmentBinding

class CommentFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val application = requireNotNull(activity).application
        val binding = CommentFragmentBinding.inflate(inflater)

        binding.setLifecycleOwner(this)

        val postProperty = CommentFragmentArgs.fromBundle(arguments!!).selectedProperty

        val viewModelFactory = CommentViewModelFactory(postProperty, application)

        binding.viewModel = ViewModelProviders.of(this, viewModelFactory).get(CommentViewModel::class.java)

        binding.commentsList.adapter = CommentAdapter()

        return binding.root
    }

}