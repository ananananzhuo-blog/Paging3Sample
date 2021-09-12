package com.ananananzhuo.paging3sample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ananananzhuo.mvvm.activity.CustomAdapterActivity
import com.ananananzhuo.mvvm.bean.bean.ItemData
import com.ananananzhuo.mvvm.callback.CallData
import com.ananananzhuo.mvvm.callback.Callback
import com.ananananzhuo.paging3sample.footerandheader.FooterHeaderActivity
import com.ananananzhuo.paging3sample.page1.Paging1Activity

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
        )
    }

    override fun showFirstItem(): Boolean {
        return false
    }

}