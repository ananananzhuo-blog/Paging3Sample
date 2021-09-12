package com.ananananzhuo.paging3sample.page1

import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn

/**
 * author  :mayong
 * function:
 * date    :2021/9/7
 **/
class BookViewModel: ViewModel() {
    val flow = Pager(
        PagingConfig(10)
    ) {
        CustomPageSource()
    }.flow.cachedIn(viewModelScope)
}