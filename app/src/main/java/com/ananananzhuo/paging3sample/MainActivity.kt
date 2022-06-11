package com.ananananzhuo.paging3sample

import android.content.Intent
import com.ananananzhuo.mvvm.activity.CustomAdapterActivity
import com.ananananzhuo.mvvm.bean.bean.ItemData
import com.ananananzhuo.mvvm.callback.CallData
import com.ananananzhuo.mvvm.callback.Callback
import com.ananananzhuo.paging3sample.footerandheader.FooterHeaderActivity
import com.ananananzhuo.paging3sample.page1.Paging1Activity
import com.ananananzhuo.paging3sample.page2.PagingRoomDeleteActivity

class MainActivity : CustomAdapterActivity() {
    override fun getAdapterDatas(): MutableList<ItemData> {
        return mutableListOf(
            ItemData(title = "基础列表实现", callback = object : Callback {
                override fun callback(callData: CallData) {
                    startActivity(Intent(this@MainActivity, Paging1Activity::class.java))
                }
            }),
            ItemData(title = "添加header和footer", callback = object : Callback {
                override fun callback(callData: CallData) {
                    startActivity(Intent(this@MainActivity, FooterHeaderActivity::class.java))
                }
            }),
            ItemData(title = "实现paging的item删除和添加", callback = object : Callback {
                override fun callback(callData: CallData) {
                    startActivity(Intent(this@MainActivity, PagingRoomDeleteActivity::class.java))
                }
            }),
        )
    }

    override fun showFirstItem(): Boolean {
        return false
    }

}