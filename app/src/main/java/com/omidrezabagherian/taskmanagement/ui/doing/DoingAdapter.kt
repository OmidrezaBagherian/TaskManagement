package com.omidrezabagherian.taskmanagement.ui.doing

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.omidrezabagherian.taskmanagement.databinding.ItemTaskBinding
import com.omidrezabagherian.taskmanagement.domian.models.Task


class DoingAdapter(private val detail: (Task) -> Unit) :
    ListAdapter<Task, DoingAdapter.DoingViewHolder>(
        object : DiffUtil.ItemCallback<Task>() {
            override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
                return oldItem.id == newItem.id
            }
        }
    ) {
    inner class DoingViewHolder(
        private val itemTaskBinding: ItemTaskBinding,
        private val detail: (Task) -> Unit
    ) : RecyclerView.ViewHolder(itemTaskBinding.root) {
        fun bind(task: Task) {
            itemTaskBinding.textViewTitle.text = task.title
            itemTaskBinding.textViewStatus.text = task.statusTask.name
            itemTaskBinding.textViewDescription.text = task.description
            itemTaskBinding.root.setOnClickListener {
                detail(task)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoingViewHolder =
        DoingViewHolder(
            ItemTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            detail
        )

    override fun onBindViewHolder(holder: DoingViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}