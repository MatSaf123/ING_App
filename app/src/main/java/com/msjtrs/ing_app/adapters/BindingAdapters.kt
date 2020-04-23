package com.msjtrs.ing_app.adapters

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.msjtrs.ing_app.domain.PostProperty
import com.msjtrs.ing_app.domain.UserProperty
import com.msjtrs.ing_app.adapters.PostAdapter
import com.msjtrs.ing_app.adapters.UserAdapter

@BindingAdapter("postData")
fun bindPostData(recyclerView: RecyclerView, data: List<PostProperty>?) {
    val adapter = recyclerView.adapter as PostAdapter
    adapter.submitList(data)
}


/*@BindingAdapter("userData")
fun bindUserData(recyclerView: RecyclerView, data: List<UserProperty>?) {
    val adapter = recyclerView.adapter as UserAdapter
    adapter.submitList(data)
}*/

