package com.msjtrs.ing_app.overview

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.msjtrs.ing_app.R
import com.msjtrs.ing_app.databinding.FragmentOverviewBinding

class OverviewFragment : Fragment() {
    private val viewModel: OverviewViewModel by lazy {
        ViewModelProviders.of(this).get(OverviewViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?) : View? {

        val binding = FragmentOverviewBinding.inflate(inflater)
        binding.setLifecycleOwner(this)

        binding.viewModel = viewModel
        binding.postsList.adapter = PostAdapter();

        //setHasOptionsMenu(true)
        //it breaks here:
        return binding.root
    }

}