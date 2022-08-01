package com.dhvc.ui_fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.dhvc.R
import com.dhvc.adapter.SubscribeforAdpter
import com.dhvc.databinding.FragmentSubscribeforBlankBinding
import com.dhvc.home.DataBeanItem
import com.dhvc.home.HomeViewmodel
import com.dhvc.toalbase.BaseFragment
import com.dhvc.ui_activity.ui.DetailsMainActivity
import com.dhvc.utils.DataRepository
import com.dhvc.utils.RemoteData
import com.dhvc.utils.observe

class Subscribefor_BlankFragment : BaseFragment<FragmentSubscribeforBlankBinding>(R.layout.fragment_subscribefor__blank) {
    var listdata =  HomeViewmodel(DataRepository(RemoteData("https://hf-android-app.s3-eu-west-1.amazonaws.com/")))
    override fun observeViewModel() {
        observe(listdata.dataLiveProvd,::data_Live)

    }
    override fun onDestroyView() {
        super.onDestroyView()
        listdata.dataLiveProvd.removeObservers(this)
    }


    fun data_Live(dataBean: ArrayList<DataBeanItem>) {
        mDataBinding.recylerview.refreshComplete()
        mDataBinding.pbLoading.visibility=View.GONE
        val recyclerView = mDataBinding.recylerview.recyclerView
        recyclerView.layoutManager= LinearLayoutManager(context)
        val subscribeforAdpter = SubscribeforAdpter(listdata, context = context, dataBean)
        subscribeforAdpter.setOnItemClickListener(object :SubscribeforAdpter.RecyclerItemListener{
            override fun onItemSelected(dataBeanItem: DataBeanItem) {
                blastoff(dataBean = dataBeanItem)
            }

        })
        recyclerView.adapter = subscribeforAdpter
    }


    fun blastoff(dataBean: DataBeanItem) {
        val intent = Intent(context,DetailsMainActivity::class.java)
        intent.putExtra("data", dataBean)
        startActivity(intent)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_refresh->{
                mDataBinding.pbLoading.visibility=View.VISIBLE
                listdata.data()
            }
        }
        return super.onOptionsItemSelected(item)
    }



    override fun initView(savedInstanceState: Bundle?) {
        listdata.data()
        mDataBinding.recylerview.setOnRefreshListener {listdata.data()}
    }

}