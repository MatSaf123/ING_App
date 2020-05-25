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

        binding.postsList.adapter = PostAdapter(
            PostAdapter.UsernameOnClickListener { viewModel.displayUserProperties(it) },
            PostAdapter.CommentsOnClickListener { viewModel.displayCommentProperties(it) },
            PostAdapter.PhotosOnClickListener { viewModel.displayPhotoProperties(it)}

        )


        viewModel.navigateToUserProperty.observe(viewLifecycleOwner, Observer {
            if(null!= it){
                this.findNavController().navigate(OverviewFragmentDirections.navigateToUser(it))
                viewModel.displayUserPropertiesComplete()
            }
        })

        viewModel.navigateToCommentProperty.observe(viewLifecycleOwner, Observer {
            if(null!= it){
                this.findNavController().navigate(OverviewFragmentDirections.navigateToComment(it))
                viewModel.displayCommentPropertiesComplete()
            }
        })

        viewModel.navigateToPhotoProperty.observe(viewLifecycleOwner, Observer {
            if(null!= it){
                this.findNavController().navigate(OverviewFragmentDirections.navigateToPhotos(it))
                viewModel.displayPhotoPropertiesComplete()
            }
        })

        //binding.usersList.adapter = UserAdapter()
        //binding.commentsList.adapter = CommentAdapter()

        return binding.root
    }


}