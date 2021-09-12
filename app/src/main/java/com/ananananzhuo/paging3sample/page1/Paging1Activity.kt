package com.ananananzhuo.paging3sample.page1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ananananzhuo.paging3sample.R
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class Paging1Activity : AppCompatActivity() {
    val model: BookViewModel by viewModels<BookViewModel> {
        ViewModelProvider.NewInstanceFactory()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paging1)
        val recycle = findViewById<RecyclerView>(R.id.recycle1)

        val adapter = CustomPage1Adapter(BookCompartor)
        recycle.layoutManager = LinearLayoutManager(this)
        recycle.adapter = adapter
        lifecycleScope.launch {
            model.flow.collectLatest {
                adapter.submitData(it)
            }
        }
    }
}