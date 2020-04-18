package com.msjtrs.ing_app

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.msjtrs.ing_app.network.PostProperty
import com.msjtrs.ing_app.network.UserProperty
import com.msjtrs.ing_app.overview.PostAdapter
import com.msjtrs.ing_app.overview.UserAdapter

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

