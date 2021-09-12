package com.ananananzhuo.paging3sample.footerandheader

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn

/**
 * author  :mayong
 * function:
 * date    :2021/9/7
 **/
class FooterHeaderViewModel : ViewModel() {
    val flow = Pager(PagingConfig(10)) {
        FHPageSource()
    }.flow.cachedIn(viewModelScope)

}