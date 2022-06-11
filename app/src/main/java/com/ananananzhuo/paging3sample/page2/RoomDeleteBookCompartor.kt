package com.ananananzhuo.paging3sample.page2

import androidx.recyclerview.widget.DiffUtil

/**
 * author  :mayong
 * function:
 * date    :2021/8/30
 **/

object RoomDeleteBookCompartor : DiffUtil.ItemCallback<RoomDeleteBook>() {
    override fun areItemsTheSame(oldItem: RoomDeleteBook, newItem: RoomDeleteBook): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: RoomDeleteBook, newItem: RoomDeleteBook): Boolean {
        return oldItem == newItem
    }
}