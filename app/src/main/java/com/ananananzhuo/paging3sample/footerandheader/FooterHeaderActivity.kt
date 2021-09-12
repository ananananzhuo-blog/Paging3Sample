package com.ananananzhuo.paging3sample.footerandheader

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ananananzhuo.paging3sample.R
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class FooterHeaderActivity : AppCompatActivity() {
    val model by viewModels<FooterHeaderViewModel> {
        ViewModelProvider.NewInstanceFactory()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_footer_header)
        val recycle = findViewById<RecyclerView>(R.id.recycle_fh)
        recycle.layoutManager = LinearLayoutManager(this)
        val adapter = DataAdapter()
        recycle.adapter =  adapter.withLoadStateFooter(footer = LoadingFooterAdapter(){
            adapter.retry()
        })

        lifecycleScope.launch {
            model.flow.collect {
                adapter.submitData(it)
            }
        }

    }
}