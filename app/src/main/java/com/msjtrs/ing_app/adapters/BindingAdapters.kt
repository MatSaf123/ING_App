package com.msjtrs.ing_app.adapters

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.msjtrs.ing_app.R
import com.msjtrs.ing_app.R.drawable.loading_animation
import com.msjtrs.ing_app.domain.PostProperty
import com.msjtrs.ing_app.domain.UserProperty
import com.msjtrs.ing_app.ui.AppStatus


@BindingAdapter("appStatus")
fun bindStatus(statusImageView: ImageView, status: AppStatus?) {
    when (status) {
        AppStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(loading_animation)
        }
        AppStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_error)
        }
        AppStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}

@BindingAdapter("postData")
fun bindPostData(recyclerView: RecyclerView, data: List<PostProperty>?) {
    val adapter = recyclerView.adapter as PostAdapter
    adapter.submitList(data)
}


@BindingAdapter("userData")
fun bindUserData(recyclerView: RecyclerView, data: List<UserProperty>?) {
    val adapter = recyclerView.adapter as UserAdapter
    adapter.submitList(data)
}