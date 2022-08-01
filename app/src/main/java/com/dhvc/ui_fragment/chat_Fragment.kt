package com.dhvc.ui_fragment

import android.app.ActionBar
import android.location.Location
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.amap.api.location.AMapLocation

import com.dhvc.R
import com.dhvc.databinding.FragmentChatBinding
import com.dhvc.toalbase.BaseFragment
import com.youth.banner.Banner
import q.rorbin.verticaltablayout.adapter.TabAdapter
import q.rorbin.verticaltablayout.widget.ITabView
import com.dhvc.MainActivity
import com.dhvc.adapter.SubscribeforAdpter
import com.dhvc.home.DataBeanItem
import com.dhvc.home.HomeViewmodel
import com.dhvc.utils.DataRepository
import com.dhvc.utils.HanziToPinyin
import com.dhvc.utils.RemoteData
import com.dhvc.utils.observe
import com.dhvc.view.IndexView
import q.rorbin.verticaltablayout.widget.ITabView.TabTitle

import q.rorbin.verticaltablayout.widget.QTabView
import com.amap.api.maps.AMap
import com.amap.api.maps.LocationSource
import com.amap.api.maps.model.MyLocationStyle
import com.amap.api.location.AMapLocationClientOption.AMapLocationMode

import com.amap.api.location.AMapLocationClientOption

import com.amap.api.location.AMapLocationClient
import com.amap.api.location.AMapLocationListener
import com.amap.api.maps.LocationSource.OnLocationChangedListener
import com.amap.api.maps.MapView


class chat_Fragment : BaseFragment<FragmentChatBinding>(R.layout.fragment_chat_) ,AMap.OnMyLocationChangeListener,
    LocationSource, AMapLocationListener {
    var listdata =  HomeViewmodel(DataRepository(RemoteData("https://hf-android-app.s3-eu-west-1.amazonaws.com/")))
    override fun observeViewModel() {
    }

    var mapView: MapView?=null
    override fun initView(savedInstanceState: Bundle?) {

        mapView = mDataBinding.mapview
        mapView!!.onCreate(savedInstanceState)
        var aMap: AMap = mapView!!.map
        val myLocationStyle = MyLocationStyle() //初始化定位蓝点样式类myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）如果不设置myLocationType，默认也会执行此种模式。

        myLocationStyle.interval(2000) //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。

        aMap.myLocationStyle = myLocationStyle //设置定位蓝点的Style

//aMap.getUiSettings().setMyLocationButtonEnabled(true);设置默认定位按钮是否显示，非必需设置。
//aMap.getUiSettings().setMyLocationButtonEnabled(true);设置默认定位按钮是否显示，非必需设置。
        aMap.isMyLocationEnabled = true // 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。

//        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_SHOW);//只定位一次。
//        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATE) ;//定位一次，且将视角移动到地图中心点。
//        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_FOLLOW) ;//连续定位、且将视角移动到地图中心点，定位蓝点跟随设备移动。（1秒1次定位）
//        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_MAP_ROTATE);//连续定位、且将视角移动到地图中心点，地图依照设备方向旋转，定位点会跟随设备移动。（1秒1次定位）
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）默认执行此种模式。
//以下三种模式从5.1.0版本开始提供
//        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE_NO_CENTER);//连续定位、蓝点不会移动到地图中心点，定位点依照设备方向旋转，并且蓝点会跟随设备移动。
//        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_FOLLOW_NO_CENTER);//连续定位、蓝点不会移动到地图中心点，并且蓝点会跟随设备移动。
//        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_MAP_ROTATE_NO_CENTER);//连续定位、蓝点不会移动到地图中心点，地图依照设备方向旋转，并且蓝点会跟随设备移动。


        myLocationStyle.showMyLocation(true)


        // 设置定位监听
        aMap.setLocationSource(this)
// 设置为true表示显示定位层并可触发定位，false表示隐藏定位层并不可触发定位，默认是false
        aMap.setMyLocationEnabled(true);
// 设置定位的类型为定位模式，有定位、跟随或地图根据面向方向旋转几种
        aMap.setMyLocationType(AMap.LOCATION_TYPE_LOCATE);
    }




    override fun onDestroyView() {
        super.onDestroyView()
        listdata.dataLiveProvd.removeObservers(this)
        mapView!!.onDestroy()
    }


    override fun onPause() {
        super.onPause()
        mapView!!.onPause()
    }


    override fun onResume() {
        super.onResume()
        mapView!!.onResume()
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapView!!.onSaveInstanceState(outState)
    }



    fun data_Live(dataBean: ArrayList<DataBeanItem>) {
        list?.addAll(dataBean)
        data?.addAll(dataBean)
        subscribeforAdpter!!.notifyDataSetChanged()

    }

    override fun onMyLocationChange(p0: Location?) {


    }
    var mListener: OnLocationChangedListener? = null
    var mlocationClient: AMapLocationClient? = null
    var mLocationOption: AMapLocationClientOption? = null
    override fun activate(p0: LocationSource.OnLocationChangedListener?) {
        mListener = p0
        if (mlocationClient == null) {
            //初始化定位
            mlocationClient = AMapLocationClient(context)
            //初始化定位参数
            mLocationOption = AMapLocationClientOption()
            //设置定位回调监听
            mlocationClient!!.setLocationListener(this)
            //设置为高精度定位模式
            mLocationOption!!.locationMode = AMapLocationMode.Hight_Accuracy
            //设置定位参数
            mlocationClient!!.setLocationOption(mLocationOption)
            // 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
            // 注意设置合适的定位时间的间隔（最小间隔支持为2000ms），并且在合适时间调用stopLocation()方法来取消定位请求
            // 在定位结束后，在合适的生命周期调用onDestroy()方法
            // 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
            mlocationClient!!.startLocation() //启动定位
        }

    }

    override fun deactivate() {
        mListener = null
        if (mlocationClient != null) {
            mlocationClient!!.stopLocation()
            mlocationClient!!.onDestroy()
        }
        mlocationClient = null
    }
    override fun onLocationChanged(p0: AMapLocation?) {


    }


}