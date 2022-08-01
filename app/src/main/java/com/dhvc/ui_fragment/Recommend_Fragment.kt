package com.dhvc.ui_fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.alibaba.android.vlayout.DelegateAdapter
import com.alibaba.android.vlayout.VirtualLayoutManager
import com.alibaba.android.vlayout.layout.GridLayoutHelper
import com.alibaba.android.vlayout.layout.SingleLayoutHelper
import com.alibaba.android.vlayout.layout.StaggeredGridLayoutHelper
import com.dhvc.R
import com.dhvc.adapter.BannerSingAdapter
import com.dhvc.adapter.griddingAdpter
import com.dhvc.adapter.gridlistAdpter
import com.dhvc.adapter.staggereAdpter
import com.dhvc.databinding.FragmentRecommendBinding
import com.dhvc.home.*
import com.dhvc.toalbase.BaseFragment
import com.dhvc.utils.DataRepository
import com.dhvc.utils.RemoteData
import com.dhvc.utils.observe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch




class Recommend_Fragment : BaseFragment<FragmentRecommendBinding>(R.layout.fragment_recommend_) {

    var banner =  BannerViewmodel(DataRepository(RemoteData("http://api-hmugo-web.itheima.net")))
    var listdata =  HomeViewmodel(DataRepository(RemoteData("https://hf-android-app.s3-eu-west-1.amazonaws.com/")))
//    var a21 =  HomeViewmodel()


    override fun onDestroyView() {
        super.onDestroyView()
        banner.bannerLiveData.removeObservers(this)
        listdata.dataLiveProvd.removeObservers(this)
    }

    private val model by viewModels<BannerViewmodel>()
    val list = ArrayList<String>()
    var ifcount = 0
    override fun observeViewModel() {
            observe(banner.bannerLiveData,::bannerurl)
            observe(listdata.dataLiveProvd,::data_Live)

    }


    fun bannerurl(image: ImageUrlBena) {
        ifcount++
        list.clear()
        GlobalScope.launch(Dispatchers.Main) {
            if (ifcount>1){
                mDataBinding.pbLoading.visibility=View.GONE
            }
            for (i in image.message.indices) {
                list.add(image.message[i].image_src)
            }
//            bannerSingAdapter?.notifyDataSetChanged()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_refresh->{
                list.clear()
                mDataBinding.pbLoading.visibility=View.VISIBLE
                banner.banner()
                listdata.data()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    var data_list = ArrayList<DataBeanItem>()
    var staggereAdpter :staggereAdpter?=null
    fun data_Live(dataBean: ArrayList<DataBeanItem>) {
        ifcount++
        if (ifcount>1){
            mDataBinding.pbLoading.visibility=View.GONE
        }
        data_list.addAll(dataBean)
        staggereAdpter?.notifyDataSetChanged()

    }
    override fun initView(savedInstanceState: Bundle?) {
        list.clear()
        data_list.clear()
        mDataBinding.tv.apply {
            text = messages.toString()
            requestFocus()}
        banner.banner()
        listdata.data()

        val virtualyayout = VirtualLayoutManager(requireActivity())
        mDataBinding.recylerview.layoutManager = virtualyayout


        val recycledViewPool = RecyclerView.RecycledViewPool()
        recycledViewPool.setMaxRecycledViews(0, 10)
        mDataBinding.recylerview.setRecycledViewPool(recycledViewPool)
        val delegateAdapter = DelegateAdapter(virtualyayout, false)
        delegateAdapter.clear()
        //轮播图
        var bannersingle = SingleLayoutHelper()
        val bannerSingAdapter = context?.let { BannerSingAdapter(bannersingle, list = list,context = it) }

        val singtitleHelper = SingleLayoutHelper()
        var griddingAdpter = griddingAdpter(context, gridLayoutHelper = singtitleHelper, title)



        var gridLayoutlistHelper = GridLayoutHelper(2)
        gridLayoutlistHelper.itemCount = 12 // 设置布局里Item个数
        gridLayoutlistHelper.setMargin(0, 10, 0, 10) // 设置LayoutHelper边缘相对父控件（即RecyclerView）的距离
//            gridLayoutlistHelper.bgColor = Color.WHITE // 设置背景颜色
        gridLayoutlistHelper.setAspectRatio((1 - 2).toFloat()) // 设置设置布局内每行布局的宽与高的比
        gridLayoutlistHelper.setAutoExpand(false) //是否自动填充空白区域5
        gridLayoutlistHelper.spanCount = 2 // 设置每行多少个网格
        var gridlistAdpter = gridlistAdpter(context = context,gridLayoutlistHelper,list)


        var staggeredGridLayoutHelper = StaggeredGridLayoutHelper(2, StaggeredGridLayoutManager.VERTICAL)
        //瀑布流滑动错位
        staggeredGridLayoutHelper.setGap(StaggeredGridLayoutManager.GAP_HANDLING_NONE)
        staggereAdpter = staggereAdpter(context = context,staggeredGridLayoutHelper,data_list)



        delegateAdapter.addAdapter(bannerSingAdapter)
        delegateAdapter.addAdapter(griddingAdpter)
        delegateAdapter.addAdapter(gridlistAdpter)
        delegateAdapter.addAdapter(staggereAdpter)
        mDataBinding.recylerview.adapter = delegateAdapter


    }


}