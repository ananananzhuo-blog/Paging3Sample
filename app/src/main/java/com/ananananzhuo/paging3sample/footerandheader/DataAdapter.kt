package com.ananananzhuo.paging3sample.footerandheader

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ananananzhuo.paging3sample.R

/**
 * author  :mayong
 * function:
 * date    :2021/9/7
 **/
class DataAdapter :
    PagingDataAdapter<Bean, DataAdapter.FooterHeaderHolder>(diffCallback = object :
        DiffUtil.ItemCallback<Bean>() {
        override fun areItemsTheSame(oldItem: Bean, newItem: Bean): Boolean = oldItem ==newItem

        override fun areContentsTheSame(oldItem: Bean, newItem: Bean): Boolean =
            oldItem == newItem
    }) {


    override fun onBindViewHolder(holder: FooterHeaderHolder, position: Int) {
        holder.tv.text = "${getItem(position)?.des}"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FooterHeaderHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_fh,parent,false)
        return FooterHeaderHolder(view)
    }

    inner class FooterHeaderHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tv: TextView = view.findViewById(R.id.tv_item)
    }
}