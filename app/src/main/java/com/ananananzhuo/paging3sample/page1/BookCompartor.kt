package com.ananananzhuo.paging3sample.page1

import androidx.recyclerview.widget.DiffUtil

/**
 * author  :mayong
 * function:
 * date    :2021/8/30
 **/

object BookCompartor : DiffUtil.ItemCallback<Book>() {
    override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean {
        return oldItem == newItem
    }
}