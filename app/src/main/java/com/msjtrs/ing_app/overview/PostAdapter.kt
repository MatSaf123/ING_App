package com.msjtrs.ing_app.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.ListAdapter
import com.msjtrs.ing_app.network.PostProperty
import com.msjtrs.ing_app.databinding.GridViewItemBinding
import androidx.recyclerview.widget.DiffUtil


class PostAdapter :
    ListAdapter<PostProperty, PostAdapter.PostViewHolder>(DiffCallback) {

    class PostViewHolder(private var binding : GridViewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(postProperty: PostProperty) {
            binding.post = postProperty
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<PostProperty>() {
        override fun areItemsTheSame(oldItem: PostProperty, newItem: PostProperty): Boolean { return oldItem === newItem }
        override fun areContentsTheSame(oldItem: PostProperty, newItem: PostProperty): Boolean { return oldItem.id == newItem.id }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(GridViewItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val postProperty = getItem(position)
        holder.bind(postProperty)
    }

}