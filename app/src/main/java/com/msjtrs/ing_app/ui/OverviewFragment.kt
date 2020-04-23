package com.msjtrs.ing_app.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.msjtrs.ing_app.adapters.PostAdapter
import com.msjtrs.ing_app.adapters.UserAdapter
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
        binding.postsList.adapter = PostAdapter()
        //binding.usersList.adapter = UserAdapter()

        return binding.root
    }

}
