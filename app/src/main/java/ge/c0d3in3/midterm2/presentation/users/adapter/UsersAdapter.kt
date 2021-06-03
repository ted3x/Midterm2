package ge.c0d3in3.midterm2.presentation.users.adapter

import android.view.ViewGroup
import android.widget.TextView
import ge.c0d3in3.midterm2.BaseAdapter
import ge.c0d3in3.midterm2.R
import ge.c0d3in3.midterm2.presentation.users.model.User

class UsersAdapter(private val onClick: (User) -> Unit) :
    BaseAdapter<User>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        BaseViewHolder(parent.inflate(R.layout.user_item_layout))

    override fun onBind(holder: BaseViewHolder, item: User) {
        holder.itemView.findViewById<TextView>(R.id.userFullNameTv).text = item.name
        holder.itemView.findViewById<TextView>(R.id.userEmailTv).text = item.email
        holder.itemView.findViewById<TextView>(R.id.userPhoneTv).text = item.phone.split(" ")[0]
        holder.itemView.setOnClickListener { onClick.invoke(item) }
    }
}