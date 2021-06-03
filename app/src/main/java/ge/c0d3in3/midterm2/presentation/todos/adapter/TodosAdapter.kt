package ge.c0d3in3.midterm2.presentation.todos.adapter

import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import ge.c0d3in3.midterm2.BaseAdapter
import ge.c0d3in3.midterm2.R
import ge.c0d3in3.midterm2.presentation.todos.model.Todo

class TodosAdapter : BaseAdapter<Todo>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        BaseViewHolder(parent.inflate(R.layout.todo_item_layout))

    override fun onBind(holder: BaseViewHolder, item: Todo) {
        holder.itemView.findViewById<TextView>(R.id.todoIdTv).text = item.id.toString()
        holder.itemView.findViewById<TextView>(R.id.todoTv).text = item.title
        val resources = holder.itemView.resources
        val color =
            if (item.completed) resources.getColor(R.color.todo_completed) else resources.getColor(R.color.white)
        holder.itemView.findViewById<ConstraintLayout>(R.id.todoLayout).setBackgroundColor(color)
    }
}