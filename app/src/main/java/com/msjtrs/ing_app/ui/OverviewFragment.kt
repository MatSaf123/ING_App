package com.msjtrs.ing_app.ui

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.msjtrs.ing_app.R
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
            PostAdapter.CommentsOnClickListener { viewModel.displayCommentProperties(it) }
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


        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?){
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item!!.itemId
        if(id == R.id.authors){
            Toast.makeText(activity,
                "Authors:\n" +
                        "Mateusz Safaryjski\n" +
                        "Jan Tański\n" +
                        "Robert Skubała\n\n" +
                        "Assets taken from flaticon.com\n" +
                        "Icon made by Freepik from www.flaticon.com\n" +
                        "Icon made by Pixel perfect from www.flaticon.com\n\n" +
                        "App version: 1.2"
                        , Toast.LENGTH_LONG).show()
        }

        return super.onOptionsItemSelected(item)
    }


}