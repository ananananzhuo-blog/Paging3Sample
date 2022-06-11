package com.ananananzhuo.paging3sample.page2

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ananananzhuo.mvvm.utils.logEE
import kotlinx.coroutines.delay

/**
 * author  :mayong
 * function:
 * date    :2021/8/30
 **/
class RoomDeleteCustomPageSource : PagingSource<Int, RoomDeleteBook>() {
    var index = 0
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RoomDeleteBook> {
        val data = params.key.let { key ->
            if (key == null) {
                listOf(RoomDeleteBook("安娜"))
            } else if (key > 10) {
                null
            } else {
                delay(300)
                listOf(
                    RoomDeleteBook("安娜"),
                    RoomDeleteBook("卡列尼亚"),
                    RoomDeleteBook("百年孤独"),
                    RoomDeleteBook("三体"),
                    RoomDeleteBook("史记"),
                    RoomDeleteBook("后汉书"),
                    RoomDeleteBook("项羽本纪"),
                    RoomDeleteBook("hello"),
                    RoomDeleteBook("world"),
                    RoomDeleteBook("教父")
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

    override fun getRefreshKey(state: PagingState<Int, RoomDeleteBook>): Int? {
        return null
    }
}