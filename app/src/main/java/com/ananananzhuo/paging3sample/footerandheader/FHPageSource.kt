package com.ananananzhuo.paging3sample.footerandheader

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ananananzhuo.mvvm.utils.logEE
import kotlinx.coroutines.delay
import java.lang.IllegalArgumentException

/**
 * author  :mayong
 * function:
 * date    :2021/9/7
 **/
class FHPageSource : PagingSource<Int, Bean>() {
    override fun getRefreshKey(state: PagingState<Int, Bean>): Int? = null
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Bean> {
        val nextKey = params.key ?: 1
        return if (nextKey > 10) {
            logEE("加载异常")
            LoadResult.Error(IllegalArgumentException("不合法"))
        } else {
            delay(1000)
            logEE("加载数据")
            LoadResult.Page(
                listOf(
                    Bean("安娜:$nextKey"),
                    Bean("李白:$nextKey"),
                    Bean("安安安安卓:$nextKey"),
                    Bean("王维:$nextKey"),
                    Bean("杜甫:$nextKey"),
                    Bean("李贺:$nextKey")
                ), prevKey = null, nextKey = nextKey + 1
            )
        }
    }
}