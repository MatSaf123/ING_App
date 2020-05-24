package com.msjtrs.ing_app.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.ListAdapter
import com.msjtrs.ing_app.domain.UserProperty
import com.msjtrs.ing_app.databinding.GridViewItemBinding
import androidx.recyclerview.widget.DiffUtil
import com.msjtrs.ing_app.databinding.CommentItemBinding
import com.msjtrs.ing_app.domain.CommentProperty


class CommentAdapter :
    ListAdapter<CommentProperty, CommentAdapter.PostViewHolder>(DiffCallback) {

    class PostViewHolder(private var binding : CommentItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(commentProperty: CommentProperty) {
            binding.comment = commentProperty
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<CommentProperty>() {
        override fun areItemsTheSame(oldItem: CommentProperty, newItem: CommentProperty): Boolean { return oldItem === newItem }
        override fun areContentsTheSame(oldItem: CommentProperty, newItem: CommentProperty): Boolean { return oldItem.id == newItem.id }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(CommentItemBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val commentProperty = getItem(position)
        holder.bind(commentProperty)
    }
}