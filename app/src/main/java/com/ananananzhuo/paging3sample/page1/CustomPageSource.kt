package com.ananananzhuo.paging3sample.page1

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ananananzhuo.mvvm.utils.logEE
import kotlinx.coroutines.delay

/**
 * author  :mayong
 * function:
 * date    :2021/8/30
 **/
class CustomPageSource : PagingSource<Int, Book>() {
    var index = 0
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Book> {
        val data = params.key.let { key ->
            if (key == null) {
                listOf(Book("安娜"))
            } else if (key > 10) {
                null
            } else {
                delay(300)
                listOf(
                    Book("安娜"),
                    Book("卡列尼亚"),
                    Book("百年孤独"),
                    Book("三体"),
                    Book("史记"),
                    Book("后汉书"),
                    Book("项羽本纪"),
                    Book("hello"),
                    Book("world"),
                    Book("教父")
                )
            }
        }
        logEE("当前加载页：${params.key}")
        return if (data == null || data.isEmpty()) {
            LoadResult.Error(NullPointerException("空指针"))
        } else {
            LoadResult.Page(
                data = data, prevKey = null, nextKey = ++index
            )
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Book>): Int? {
        return null
    }
}