package com.ananananzhuo.paging3sample.page1

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.paging.DifferCallback
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

/**
 * author  :mayong
 * function:
 * date    :2021/8/30
 **/
class CustomPage1Adapter(differCallback: DiffUtil.ItemCallback<Book>) :
    PagingDataAdapter<Book, CustomPage1Holder>(differCallback) {
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: CustomPage1Holder, position: Int) {
        holder.btn.text = "${getItem(position)?.name}:$position"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomPage1Holder {
        return CustomPage1Holder(Button(parent.context))
    }
}

class CustomPage1Holder(val btn: Button) : RecyclerView.ViewHolder(btn) {

}