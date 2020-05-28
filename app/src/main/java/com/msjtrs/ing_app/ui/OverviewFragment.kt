package com.msjtrs.ing_app.ui

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.AbsListView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.msjtrs.ing_app.R
import com.msjtrs.ing_app.adapters.PostAdapter
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

        binding.postsList.addOnScrollListener(onScrollListener)

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

    var isScrolling = false
    private val onScrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val layoutManager = recyclerView.layoutManager as LinearLayoutManager
            val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
            val visibleItemCount = layoutManager.childCount
            val totalItemCount = layoutManager.itemCount

            val lastItem = firstVisibleItemPosition + visibleItemCount >= totalItemCount
            val notAtBeginning = firstVisibleItemPosition >= 0
            val moreThanVisible = totalItemCount >= 10
            val checkIfToLoad =  lastItem && notAtBeginning && moreThanVisible && isScrolling

            if(checkIfToLoad) {
                val message : String
                if(viewModel.loadMorePosts())
                    message = "Loading more posts!"
                else
                    message  = "No more posts to load"
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                isScrolling = false
            }
        }

        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if(newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
                isScrolling = true
            }
        }
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