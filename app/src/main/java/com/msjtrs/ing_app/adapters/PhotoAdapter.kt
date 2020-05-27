package com.msjtrs.ing_app.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.ListAdapter
import com.msjtrs.ing_app.domain.UserProperty
import com.msjtrs.ing_app.databinding.GridViewItemBinding
import androidx.recyclerview.widget.DiffUtil
import com.msjtrs.ing_app.databinding.CommentItemBinding
import com.msjtrs.ing_app.databinding.PhotoItemBinding
import com.msjtrs.ing_app.domain.CommentProperty
import com.msjtrs.ing_app.domain.PhotoProperty
import com.msjtrs.ing_app.domain.PostProperty
import kotlinx.android.synthetic.main.user_fragment.view.*


class PhotoAdapter :
    ListAdapter<PhotoProperty, PhotoAdapter.PostViewHolder>(DiffCallback) {

    class PostViewHolder(private var binding : PhotoItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(photoProperty: PhotoProperty) {
            binding.photo = photoProperty
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<PhotoProperty>() {
        override fun areItemsTheSame(oldItem: PhotoProperty, newItem: PhotoProperty): Boolean { return oldItem === newItem }
        override fun areContentsTheSame(oldItem: PhotoProperty, newItem: PhotoProperty): Boolean { return oldItem.id == newItem.id }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(PhotoItemBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val photoProperty = getItem(position)
        holder.bind(photoProperty)
    }


}