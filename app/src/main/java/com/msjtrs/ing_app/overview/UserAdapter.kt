package com.msjtrs.ing_app.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.ListAdapter
import com.msjtrs.ing_app.network.UserProperty
import com.msjtrs.ing_app.databinding.GridViewItemBinding
import androidx.recyclerview.widget.DiffUtil


class UserAdapter :
    ListAdapter<UserProperty, UserAdapter.PostViewHolder>(DiffCallback) {

    class PostViewHolder(private var binding : GridViewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(userProperty: UserProperty) {
            binding.user = userProperty
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<UserProperty>() {
        override fun areItemsTheSame(oldItem: UserProperty, newItem: UserProperty): Boolean { return oldItem === newItem }
        override fun areContentsTheSame(oldItem: UserProperty, newItem: UserProperty): Boolean { return oldItem.id == newItem.id }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(GridViewItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val userProperty = getItem(position)
        holder.bind(userProperty)
    }
}