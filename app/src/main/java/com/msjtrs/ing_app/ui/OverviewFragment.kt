package com.msjtrs.ing_app.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.msjtrs.ing_app.adapters.CommentAdapter
import com.msjtrs.ing_app.adapters.PostAdapter
import com.msjtrs.ing_app.adapters.UserAdapter
import com.msjtrs.ing_app.databinding.FragmentOverviewBinding


class OverviewFragment : Fragment(){
    private val viewModel: OverviewViewModel by lazy {
        ViewModelProviders.of(this).get(OverviewViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?) : View? {

        val binding = FragmentOverviewBinding.inflate(inflater)

        binding.setLifecycleOwner(this)

        binding.viewModel = viewModel

        //twój kod
        binding.postsList.adapter = PostAdapter(
            PostAdapter.OnClickListener { viewModel.displayPropertyDetails(it) },
            PostAdapter.CommentsOnClickListener { viewModel.displayPropertyDetails(it) }
        )

        //mój stary kod
//        binding.postsList.adapter = PostAdapter(PostAdapter.OnClickListener{
//            viewModel.displayPropertyDetails(it)
//        })

        viewModel.navigateToSelectedProperty.observe(viewLifecycleOwner, Observer {
            if(null!= it){
                this.findNavController().navigate(OverviewFragmentDirections.actionShowDetail(it))
                viewModel.displayPropertyDetailsComplete()
            }
        })

        binding.usersList.adapter = UserAdapter()
        binding.commentsList.adapter = CommentAdapter()

        return binding.root
    }


}