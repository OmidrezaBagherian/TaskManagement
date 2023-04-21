package com.omidrezabagherian.taskmanagement.ui.done

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.omidrezabagherian.taskmanagement.databinding.ItemTaskBinding
import com.omidrezabagherian.taskmanagement.domian.models.Task


class DoneAdapter(private val detail: (Task) -> Unit) :
    ListAdapter<Task, DoneAdapter.DoneViewHolder>(
        object : DiffUtil.ItemCallback<Task>() {
            override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
                return oldItem.id == newItem.id
            }
        }
    ) {
    inner class DoneViewHolder(
        private val itemTaskBinding: ItemTaskBinding,
        private val detail: (Task) -> Unit
    ) : RecyclerView.ViewHolder(itemTaskBinding.root) {
        fun bind(task: Task) {
            itemTaskBinding.tvTitle.text = task.title
            itemTaskBinding.tvStatus.text = task.taskStatus.name
            itemTaskBinding.tvDescription.text = task.description
            itemTaskBinding.root.setOnClickListener {
                detail(task)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoneViewHolder =
        DoneViewHolder(
            ItemTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            detail
        )

    override fun onBindViewHolder(holder: DoneViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}