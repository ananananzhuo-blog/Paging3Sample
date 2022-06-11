package com.ananananzhuo.paging3sample.page2

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * author  :mayong
 * function:
 * date    :2021/8/30
 **/
@Entity
data class RoomDeleteBook(
    var name: String,
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null,
)