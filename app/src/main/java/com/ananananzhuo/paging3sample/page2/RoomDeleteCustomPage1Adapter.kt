package com.ananananzhuo.paging3sample.page2

import android.annotation.SuppressLint
import android.view.ViewGroup
import android.widget.Button
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

/**
 * author  :mayong
 * function:
 * date    :2021/8/30
 **/
class RoomDeleteCustomPage1Adapter(differCallback: DiffUtil.ItemCallback<RoomDeleteBook>,val deleteCallback:(RoomDeleteBook?)->Unit) :
    PagingDataAdapter<RoomDeleteBook, RoomDeleteCustomPage1Holder>(differCallback) {
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RoomDeleteCustomPage1Holder, position: Int) {
        holder.btn.text = "${getItem(position)?.name}:$position   ,点击删除"
        holder.btn.setOnClickListener {
            deleteCallback(getItem(position))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomDeleteCustomPage1Holder {
        return RoomDeleteCustomPage1Holder(Button(parent.context))
    }
}

class RoomDeleteCustomPage1Holder(val btn: Button) : RecyclerView.ViewHolder(btn) {

}