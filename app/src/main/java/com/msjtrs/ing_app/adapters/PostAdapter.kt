package com.msjtrs.ing_app.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.msjtrs.ing_app.databinding.GridViewItemBinding
import com.msjtrs.ing_app.domain.PostProperty
import kotlinx.android.synthetic.main.grid_view_item.view.*


class PostAdapter(
    private val usernameOnClickListener: UsernameOnClickListener,
    private  val onCommentsOnClickListener: CommentsOnClickListener) :
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
        return PostViewHolder(GridViewItemBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val postProperty = getItem(position)

        holder.bind(postProperty)       //mozna tu liste dac
        holder.itemView.id_user.setOnClickListener {
            usernameOnClickListener.onClick(postProperty)
        }
        holder.itemView.id_comment.setOnClickListener {
            onCommentsOnClickListener.onClick(postProperty)
        }


    }

    class UsernameOnClickListener(val clickListener: (postProperty: PostProperty) -> Unit){
        fun onClick(postProperty: PostProperty) = clickListener(postProperty)
    }

    class CommentsOnClickListener(val clickListener: (postProperty: PostProperty) -> Unit){
        fun onClick(postProperty: PostProperty) = clickListener(postProperty)
    }
}