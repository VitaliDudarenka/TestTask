package com.gmail.dudarenka.vitali.testtask.presentation.screen.user.list

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.gmail.dudarenka.vitali.domain.entity.User
import com.gmail.dudarenka.vitali.testtask.R
import android.text.method.TextKeyListener.clear


class UserListAdapter : RecyclerView.Adapter<UserListAdapter.Holder>() {
    private var userList: ArrayList<User>? = ArrayList()
    var listData: MutableList<User>? = ArrayList()
        set(value) {
            field = value
            notifyDataSetChanged()
            userList!!.addAll(this.listData!!)
        }
    internal var onItemClickListener: OnItemClickListener? = null

    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): Holder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.user_item, viewGroup, false)
        return Holder(view)
    }

    override fun getItemCount(): Int = listData!!.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val user = listData!![position]
        holder.loginTextView.text = user.login
        holder.idTextView.text = user.id
        Glide.with(holder.itemView.context)
                .load(user.avatarUrl)
                .into(holder.avaImageView)
    }

    inner class Holder : RecyclerView.ViewHolder {
        var loginTextView: TextView
        var idTextView: TextView
        var avaImageView: ImageView

        constructor(view: View) : super(view) {
            loginTextView = view.findViewById(R.id.loginTextView)
            idTextView = view.findViewById(R.id.idTextView)
            avaImageView = view.findViewById(R.id.avaImageView)
            itemView.setOnClickListener {
                val user = listData!![layoutPosition]
                onItemClickListener!!.onItemClick(user)
            }
        }

    }

    interface OnItemClickListener {
        fun onItemClick(user: User)
    }

    fun getLastId(): Int {
        var id = 0
        for (user in this.listData!!) {
            if (user.id.toInt() > id)
                id = user.id.toInt()
        }
        return id
    }

    fun clear() {
        listData!!.clear()
        notifyDataSetChanged()
    }


}