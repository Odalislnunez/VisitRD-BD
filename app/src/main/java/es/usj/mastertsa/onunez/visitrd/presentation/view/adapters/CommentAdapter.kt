package es.usj.mastertsa.onunez.visitrd.presentation.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import es.usj.mastertsa.onunez.visitrd.databinding.ItemCommentBinding
import es.usj.mastertsa.onunez.visitrd.domain.model.Comment

class CommentAdapter(private val placeCode: Int): ListAdapter<Comment, CommentAdapter.CommentViewHolder>(CommentsDiffUtilCallback) {
    inner class CommentViewHolder(val binding: ItemCommentBinding): RecyclerView.ViewHolder(binding.root)

    public override fun getItem(position: Int): Comment {
        return super.getItem(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val binding = ItemCommentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CommentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        val comment = getItem(position)

        if(comment.place_code == placeCode){
            holder.binding.tvComment.text = comment.comment
            holder.binding.tvUser.text = comment.user
        }
    }
}

object CommentsDiffUtilCallback: DiffUtil.ItemCallback<Comment>() {
    override fun areItemsTheSame(oldItem: Comment, newItem: Comment): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Comment, newItem: Comment): Boolean {
        return oldItem.id == newItem.id
    }
}