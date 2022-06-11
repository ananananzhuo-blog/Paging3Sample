package com.ananananzhuo.paging3sample.page2.room

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed
import androidx.room.*
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

/**
 * author  :mayong
 * function:在room数据库中加载数据
 * date    :2022/4/17
 **/


class RoomLoadPageModel : ViewModel() {
    //    val db =
//        Room.databaseBuilder(App.app, GoodsDatabase::class.java, "goods")
//            .allowMainThreadQueries()
//            .build()
//    val db =
//        Room.inMemoryDatabaseBuilder(App.app, GoodsDatabase::class.java)
//            .allowMainThreadQueries()
//            .build()
//    private var count = 0
//
//    @OptIn(ExperimentalPagingApi::class)
//    val projects = Pager(
//        PagingConfig(pageSize = 20),
//        remoteMediator = RoomRemoteMediator(callback = { loadType, pagingState ->
//            if (count>20){
//                RemoteMediator.MediatorResult.Success(true)
//            }else{
//                Goods("商品：${++count}").let {
//                    db.goodsDao().insertAll(it)
//                }
//                RemoteMediator.MediatorResult.Success(false)
//            }
//        }),
//    ) {
//        val start = System.currentTimeMillis()
//        val data = db.goodsDao().getAll1()
//        data
//    }.flow.cachedIn(viewModelScope)
//
//    fun insert() {
//        viewModelScope.launch {
//            db.goodsDao().insertAll(listOf(Goods(name = "aaa${++count}")))
//        }
//    }
//
//    fun delete(goods: Goods) {
//        db.goodsDao().delete(goods)
//    }
//
//    override fun onCleared() {
//        super.onCleared()
//        db.clearAllTables()
//    }
//
//    /**
//     * 更新item数据
//     */
//    fun modify(goods: Goods, index: Int) {
//        db.goodsDao().update(goods.apply {
//            name = "---名字被修改"
//        })
//    }
}

//class RoomLoadPageSource(val callback: suspend (params: LoadParams<Int>) -> LoadResult<Int, Goods>) :
//    PagingSource<Int, Goods>() {
//    override fun getRefreshKey(state: PagingState<Int, Goods>): Int? = null
//
//    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Goods> {
//        return callback(params)
//    }
//}
//
//@OptIn(ExperimentalPagingApi::class)
//class RoomRemoteMediator(val callback: suspend (LoadType, PagingState<Int, Goods>) -> MediatorResult) :
//    RemoteMediator<Int, Goods>() {
//    override suspend fun load(loadType: LoadType, state: PagingState<Int, Goods>): MediatorResult {
//        return callback(loadType, state)
//    }
//
//}
//
//@Dao
//interface GoodsDao {
//    @Query("SELECT * FROM goods order by goodsName ASC")
//    fun getAll(): List<Goods>
//
//    @Query("SELECT * FROM goods")
//    fun getAll1(): PagingSource<Int, Goods>
//
//    @Insert(onConflict = OnConflictStrategy.IGNORE)
//    fun insertAll(vararg users: Goods)
//
//    @Insert
//    fun insertAll(users: List<Goods>)
//
//    @Delete
//    fun delete(user: Goods)
//
//    @Update
//    fun update(goods: Goods)
//}
//
//@Entity
//data class Goods(
//    @PrimaryKey(autoGenerate = false)
//    @ColumnInfo(name = "goodsName")
//    var name: String,
//) {
////    @PrimaryKey(autoGenerate = true)
////    var id: Long? = null
//}
//
//@Database(entities = arrayOf(Goods::class), version = 1)
//abstract class GoodsDatabase : RoomDatabase() {
//    abstract fun goodsDao(): GoodsDao
//}
