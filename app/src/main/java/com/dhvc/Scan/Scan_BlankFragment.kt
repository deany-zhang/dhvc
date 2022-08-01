package com.dhvc.Scan

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import cn.bingoogolapple.qrcode.core.QRCodeView
import com.dhvc.R
import com.dhvc.toalbase.BaseFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.HashMap
import kotlin.concurrent.thread


class Scan_BlankFragment constructor(private var scanDelegate :ScanDelegate) : BaseFragment<com.dhvc.databinding.LayoutScanBinding>(R.layout.layout_scan),QRCodeView.Delegate {
    override fun observeViewModel() {}

     fun start(){
        GlobalScope.launch(Dispatchers.Main) {
            delay(500)
            mDataBinding?.zxingview?.startSpotAndShowRect() // 显示扫描框，并开始识别
            delay(200)
            mDataBinding?.zxingview?.startCamera() // 打开后置摄像头开始预览，但是并未开始识别
        }
    }


    private val handler = Handler()
    override fun initView(savedInstanceState: Bundle?) {
        var clickNum =0
        mDataBinding.con.setOnClickListener(View.OnClickListener {
            clickNum++
            handler.postDelayed({
                if (clickNum == 1) {
                } else if (clickNum == 2) {
                    onDestroy()
                }
                handler.removeCallbacksAndMessages(null)
                clickNum = 0
            }, 300)
        })

        mDataBinding.zxingview.setDelegate(object : QRCodeView.Delegate {
            override fun onScanQRCodeSuccess(result: String?) {
                if (result != null) {
                   scanDelegate.onScanQRCodeSuccess(result)
                }
            }

            override fun onCameraAmbientBrightnessChanged(isDark: Boolean) {

                // 这里是通过修改提示文案来展示环境是否过暗的状态，接入方也可以根据 isDark 的值来实现其他交互效果
                var tipText: String? = mDataBinding?.zxingview?.scanBoxView?.tipText
                val ambientBrightnessTip = "\n环境过暗，请打开闪光灯"
                if (isDark) {
                    if (!tipText!!.contains(ambientBrightnessTip)) {
                        mDataBinding?.zxingview?.scanBoxView?.tipText = tipText + ambientBrightnessTip
                    }
                } else {
                    if (tipText!!.contains(ambientBrightnessTip)) {
                        tipText = tipText.substring(0, tipText.indexOf(ambientBrightnessTip))
                        mDataBinding?.zxingview?.scanBoxView?.tipText = tipText
                    }
                }
            }

            override fun onScanQRCodeOpenCameraError() {
               scanDelegate.onScanQRCodeOpenCameraError()
            }

        })
    }

    override fun onStart() {
        super.onStart()
        GlobalScope.launch(Dispatchers.Main) {
            delay(500)
            mDataBinding?.zxingview?.startSpotAndShowRect() // 显示扫描框，并开始识别
            delay(200)
            mDataBinding?.zxingview?.startCamera() // 打开后置摄像头开始预览，但是并未开始识别
        }

    }

    override fun onStop() {
        super.onStop()
        mDataBinding?.zxingview?.stopCamera() // 关闭摄像头预览，并且隐藏扫描框
    }

    override fun onDestroy() {
        super.onDestroy()
        mDataBinding?.zxingview?.onDestroy() // 关闭摄像头预览，并且隐藏扫描框
    }

    override fun onScanQRCodeSuccess(result: String?) {
        Toast.makeText(context,result,Toast.LENGTH_LONG)
            .show()
    }

    override fun onCameraAmbientBrightnessChanged(isDark: Boolean) {

        // 这里是通过修改提示文案来展示环境是否过暗的状态，接入方也可以根据 isDark 的值来实现其他交互效果
        var tipText: String? = mDataBinding?.zxingview?.scanBoxView?.tipText
        val ambientBrightnessTip = "\n环境过暗，请打开闪光灯"
        if (isDark) {
            if (!tipText!!.contains(ambientBrightnessTip)) {
                mDataBinding?.zxingview?.scanBoxView?.tipText = tipText + ambientBrightnessTip
            }
        } else {
            if (tipText!!.contains(ambientBrightnessTip)) {
                tipText = tipText.substring(0, tipText.indexOf(ambientBrightnessTip))
                mDataBinding?.zxingview?.scanBoxView?.tipText = tipText
            }
        }
    }

    override fun onScanQRCodeOpenCameraError() {

    }




}