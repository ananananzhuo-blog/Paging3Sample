package com.ananananzhuo.paging3sample.footerandheader

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ananananzhuo.paging3sample.R

/**
 * author  :mayong
 * function:
 * date    :2021/9/7
 **/
class LoadingFooterAdapter(val retry: () -> Unit) :
    LoadStateAdapter<LoadingFooterAdapter.FooterHolder>() {
    override fun onBindViewHolder(holder: FooterHolder, loadState: LoadState) {
        when (loadState) {
            is LoadState.Error -> {
                holder.itemView.visibility = View.GONE
            }
            is LoadState.Loading -> {
                holder.itemView.visibility = View.VISIBLE
            }
            is LoadState.NotLoading -> {
                holder.itemView.visibility = View.GONE
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): FooterHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_footer_loading, parent, false)
        return FooterHolder(view)
    }

    inner class FooterHolder(view: View) : RecyclerView.ViewHolder(view) {

    }
}