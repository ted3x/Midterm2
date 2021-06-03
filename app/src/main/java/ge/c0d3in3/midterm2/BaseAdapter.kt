package ge.c0d3in3.midterm2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T>: RecyclerView.Adapter<BaseAdapter.BaseViewHolder>() {

    protected var items: List<T> = listOf()

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        onBind(holder, items[position])
    }

    abstract fun onBind(holder: BaseViewHolder, item: T)

    fun setAdapterItems(items: List<T>) {
        this.items = items
        notifyDataSetChanged()
    }

    open class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    fun ViewGroup.inflate(layout: Int) = LayoutInflater.from(this.context).inflate(layout, this, false)
}