package com.msjtrs.ing_app

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.msjtrs.ing_app.network.PostProperty
import com.msjtrs.ing_app.overview.PostAdapter

@BindingAdapter("listData")
fun bindListData(recyclerView: RecyclerView, data: List<PostProperty>?) {
    val adapter = recyclerView.adapter as PostAdapter
    adapter.submitList(data)
}