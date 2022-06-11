package com.ananananzhuo.paging3sample.page2

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import androidx.room.*
import com.ananananzhuo.paging3sample.App
import kotlinx.coroutines.launch

/**
 * author  :mayong
 * function:
 * date    :2021/9/7
 **/
class RoomDeleteBookViewModel: ViewModel() {

    val db =
        Room.inMemoryDatabaseBuilder(App.app, GoodsDatabase::class.java)
            .allowMainThreadQueries()
            .build()
    private var count = 0

    @OptIn(ExperimentalPagingApi::class)
    val flow = Pager(
        PagingConfig(pageSize = 20),
        remoteMediator = RoomRemoteMediator(callback = { loadType, pagingState ->
            if (count>20){
                RemoteMediator.MediatorResult.Success(true)
            }else{
                RoomDeleteBook(name = "商品：${++count}").let {
                    db.goodsDao().insertAll(it)
                }
                RemoteMediator.MediatorResult.Success(false)
            }
        }),
    ) {
        val data = db.goodsDao().getAll1()
        data
    }.flow.cachedIn(viewModelScope)

    fun insert() {
        viewModelScope.launch {
            db.goodsDao().insertAll(listOf(RoomDeleteBook(name = "aaa${++count}")))
        }
    }

    fun delete(goods: RoomDeleteBook?) {
        goods?.let {
            db.goodsDao().delete(it)
        }
    }
}
@OptIn(ExperimentalPagingApi::class)
class RoomRemoteMediator(val callback: suspend (LoadType, PagingState<Int, RoomDeleteBook>) -> MediatorResult) :
    RemoteMediator<Int, RoomDeleteBook>() {
    override suspend fun load(loadType: LoadType, state: PagingState<Int, RoomDeleteBook>): MediatorResult {
        return callback(loadType, state)
    }
}

@Dao
interface GoodsDao {
    @Query("SELECT * FROM RoomDeleteBook order by name ASC")
    fun getAll(): List<RoomDeleteBook>

    @Query("SELECT * FROM RoomDeleteBook")
    fun getAll1(): PagingSource<Int, RoomDeleteBook>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(vararg users: RoomDeleteBook)

    @Insert
    fun insertAll(users: List<RoomDeleteBook>)

    @Delete
    fun delete(user: RoomDeleteBook)

    @Update
    fun update(goods: RoomDeleteBook)
}


@Database(entities = [RoomDeleteBook::class], version = 1, exportSchema = false)
abstract class GoodsDatabase : RoomDatabase() {
    abstract fun goodsDao(): GoodsDao
}
