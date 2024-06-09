package com.example.kursovaya

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.kursovaya.databinding.UserItemBinding


class  UsersAdapter : RecyclerView.Adapter<UsersAdapter.UsrHolder>() {

    private val usrList = ArrayList<UserItem>()

    class UsrHolder(item: View) : RecyclerView.ViewHolder(item) {
        val binding = UserItemBinding.bind(item)

        fun bind(userItem: UserItem) = with(binding) {

            tvUserRole.text = userItem.name

            when (userItem.status) {
                1 -> {
                    tvFullName.text = "User"
                    tvFullName.setTextColor(ContextCompat.getColor(itemView.context, R.color.color_user))
                }
                2 -> {
                    tvFullName.text = "Courier"
                    tvFullName.setTextColor(ContextCompat.getColor(itemView.context, R.color.color_courier))
                }
                3 -> {
                    tvFullName.text = "Admin"
                    tvFullName.setTextColor(ContextCompat.getColor(itemView.context, R.color.color_admin))
                }
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsrHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.his_item, parent, false)
        return UsrHolder(view)
    }

    override fun getItemCount(): Int {
        return usrList.size
    }

    override fun onBindViewHolder(holder: UsrHolder, position: Int) {
        holder.bind(usrList[position])
    }

    fun updateDataUser(newList: List<UserItem>) {
        usrList.clear()
        usrList.addAll(newList)
        notifyDataSetChanged()
    }
}
