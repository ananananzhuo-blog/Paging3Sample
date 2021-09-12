

关注公众号学习更多知识

![](https://files.mdnice.com/user/15648/404c2ab2-9a89-40cf-ba1c-02df017a4ae8.jpg)



## 功能概览

- Paging可以将数据在内存中缓存，从而高效利用系统资源
- 内置重复的请求信息删除，从而高效利用网络
- 可配置Recycleview适配器，从而在用户滚动到尾部时自动加载数据
- 对Flow、Livedata、RxJava都可以支持
- 内置错误处理功能，包括刷新和重试

## 架构分级

![](https://files.mdnice.com/user/15648/b1c2a358-b836-4251-89ae-178a86c19c86.png)

### 代码库层
`PagingSource和RemoteMeditor`是Paging的数据源，`PagingSource`可以从单个数据源获取数据，`RemoteMeditor`可以从混合数据源获取数据
### ViewModel层
Pager 组件提供了一个公共 API，基于 PagingSource 对象和 PagingConfig 配置对象来构造在响应式流中公开的 PagingData 实例。

将 ViewModel 层连接到界面的组件是 PagingData。PagingData 对象是用于存放分页数据快照的容器。它会查询 PagingSource 对象并存储结果。

### 
界面层中的主要 Paging 库组件是 PagingDataAdapter，它是一种处理分页数据的 RecyclerView 适配器。

此外，您也可以使用随附的 AsyncPagingDataDiffer 组件来构建自己的自定义适配器。



## 使用

### 依赖

```groovy
dependencies {
  val paging_version = "3.0.1"

  implementation("androidx.paging:paging-runtime:$paging_version")

  // alternatively - without Android dependencies for tests
  testImplementation("androidx.paging:paging-common:$paging_version")

  // optional - RxJava2 support
  implementation("androidx.paging:paging-rxjava2:$paging_version")

  // optional - RxJava3 support
  implementation("androidx.paging:paging-rxjava3:$paging_version")

  // optional - Guava ListenableFuture support
  implementation("androidx.paging:paging-guava:$paging_version")

  // optional - Jetpack Compose integration
  implementation("androidx.paging:paging-compose:1.0.0-alpha12")
}
```

### 基础使用
#### 各个api的作用
使用Paging需要自定义几个类

`PagingDataAdapter：`自定义adapter

`PagingSource<Int, Book>`：自定义数据源，泛型第一个参数是查询数据的key，一般使用Int类型，其它类型亦可

` DiffUtil.ItemCallback<Book>()：`自定义去重机制，内部有两个方法，分别是`areItemsTheSame  ： areContentsTheSame`。其中`areItemsTheSame`用来判断两个item是否是相同的对象一般用来比对对象地址是否相同，`areContentsTheSame`一般用来比对两个对象内部的数据是否相同，比如你如果返回多个相同的字符串那有可能会被认为都是重复的。

`Pager`用来处理配置相关内容

#### 简单使用Paging加载数据代码

##### 适配器
```kt
class CustomPage1Adapter(differCallback: DiffUtil.ItemCallback<Book>) :
    PagingDataAdapter<Book, CustomPage1Holder>(differCallback) {
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: CustomPage1Holder, position: Int) {
        holder.btn.text = "${getItem(position)?.name}:$position"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomPage1Holder {
        return CustomPage1Holder(Button(parent.context))
    }
}

class CustomPage1Holder(val btn: Button) : RecyclerView.ViewHolder(btn) {

}
```
##### PagingSource

```kt

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
```
##### DiffUtil.ItemCallback

```kt
object BookCompartor : DiffUtil.ItemCallback<Book>() {
    override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean {
        return oldItem == newItem
    }
}
```
##### 配置代码

```kt
val flow = Pager(
        PagingConfig(10)//设置每页数据为10条
    ) {
        CustomPageSource()//数据源
    }.flow.cachedIn(viewModelScope)//在ViewModel中缓存数据，这样做可避免配置更改的时候数据丢失
```

##### 初始化Paging方式

```kt
        val recycle = findViewById<RecyclerView>(R.id.recycle1)
        val adapter = CustomPage1Adapter(BookCompartor)
        recycle.layoutManager = LinearLayoutManager(this)
        recycle.adapter = adapter
        lifecycleScope.launch {
            model.flow.collectLatest {
                adapter.submitData(it)//提交数据到适配器
            }
        }
```

### 给Paging添加Footer
直接上代码

#### DataAdapter数据适配器

```
class DataAdapter :
    PagingDataAdapter<Bean, DataAdapter.FooterHeaderHolder>(diffCallback = object :
        DiffUtil.ItemCallback<Bean>() {
        override fun areItemsTheSame(oldItem: Bean, newItem: Bean): Boolean = oldItem ==newItem

        override fun areContentsTheSame(oldItem: Bean, newItem: Bean): Boolean =
            oldItem == newItem
    }) {


    override fun onBindViewHolder(holder: FooterHeaderHolder, position: Int) {
        holder.tv.text = "${getItem(position)?.des}"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FooterHeaderHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_fh,parent,false)
        return FooterHeaderHolder(view)
    }

    inner class FooterHeaderHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tv: TextView = view.findViewById(R.id.tv_item)
    }
}
```

#### 底部加载中的Footer

底部的Footer也是用适配器的方式实现，在初始化阶段会通过`withLoadStateFooter`方法适配到数据适配器`DataAdapter`中

```kt
class LoadingFooterAdapter(val retry: () -> Unit) :
    LoadStateAdapter<LoadingFooterAdapter.FooterHolder>() {
    override fun onBindViewHolder(holder: FooterHolder, loadState: LoadState) {
        when (loadState) {
            is LoadState.Error -> {
                holder.itemView.visibility = View.GONE
            }
            is LoadState.Loading -> {
                holder.itemView.visibility = View.VISIBLE
            }
            is LoadState.NotLoading -> {
                holder.itemView.visibility = View.GONE
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): FooterHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_footer_loading, parent, false)
        return FooterHolder(view)
    }

    inner class FooterHolder(view: View) : RecyclerView.ViewHolder(view) {

    }
}
```

#### 数据源Source

```kt
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
```

#### Pager配置

```kt
    val flow = Pager(PagingConfig(10)) {
        FHPageSource()
    }.flow.cachedIn(viewModelScope)
```
#### 初始化代码

```kt
        val recycle = findViewById<RecyclerView>(R.id.recycle_fh)
        recycle.layoutManager = LinearLayoutManager(this)
        val adapter = DataAdapter()
        recycle.adapter =  adapter.withLoadStateFooter(footer = LoadingFooterAdapter(){
            adapter.retry()
        })//这一句代码是关键

        lifecycleScope.launch {
            model.flow.collect {
                adapter.submitData(it)
            }
        }
```









