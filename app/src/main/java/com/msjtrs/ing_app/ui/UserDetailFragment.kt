package com.msjtrs.ing_app.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.msjtrs.ing_app.databinding.FragmentDetailBinding

class UserDetailFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        @Suppress("UNUSED_VARIABLE")
        val application = requireNotNull(activity).application
        val binding = FragmentDetailBinding.inflate(inflater)

        binding.setLifecycleOwner(this)

        val postProperty = UserDetailFragmentArgs.fromBundle(arguments!!).selectedProperty

        val viewModelFactory = UserDetailViewModelFactory(postProperty, application)

        binding.viewModel = ViewModelProviders.of(this, viewModelFactory).get(UserDetailViewModel::class.java)
        return binding.root
    }
}