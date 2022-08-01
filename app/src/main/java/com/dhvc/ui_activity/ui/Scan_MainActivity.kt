package com.dhvc.ui_activity.ui

import android.widget.Toast
import cn.bingoogolapple.qrcode.core.QRCodeView
import com.dhvc.MainActivity
import com.dhvc.Scan.ScanDelegate
import com.dhvc.databinding.LayoutScanBinding
import com.dhvc.toalbase.BaseActivity

class Scan_MainActivity : BaseActivity(),QRCodeView.Delegate {
    private val REQUEST_CODE_CHOOSE_QRCODE_FROM_GALLERY = 666
    private val REQUEST_CODE_QRCODE_PERMISSIONS = 1
    var scanDelegate :ScanDelegate =MainActivity()
    override fun observeViewModel() {


    }

    override fun initView() {
        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }

        binding?.zxingview?.setOnClickListener {
            Toast.makeText(this,"ewew",Toast.LENGTH_LONG).show()
            finish() }
        binding?.con?.setOnClickListener {
            Toast.makeText(this,"ewew",Toast.LENGTH_LONG).show()
            finish() }
        binding?.zxingview?.setDelegate(object : QRCodeView.Delegate {
            override fun onScanQRCodeSuccess(result: String?) {
                if (result != null) {
                    Toast.makeText(this@Scan_MainActivity,result,Toast.LENGTH_LONG).show()
                    finish()
                    scanDelegate?.onScanQRCodeSuccess(result)
                }
            }

            override fun onCameraAmbientBrightnessChanged(isDark: Boolean) {

                // 这里是通过修改提示文案来展示环境是否过暗的状态，接入方也可以根据 isDark 的值来实现其他交互效果
                var tipText: String? = binding?.zxingview?.scanBoxView?.tipText
                val ambientBrightnessTip = "\n环境过暗，请打开闪光灯"
                if (isDark) {
                    if (!tipText!!.contains(ambientBrightnessTip)) {
                        binding?.zxingview?.scanBoxView?.tipText = tipText + ambientBrightnessTip
                    }
                } else {
                    if (tipText!!.contains(ambientBrightnessTip)) {
                        tipText = tipText.substring(0, tipText.indexOf(ambientBrightnessTip))
                        binding?.zxingview?.scanBoxView?.tipText = tipText
                    }
                }
            }

            override fun onScanQRCodeOpenCameraError() {
                scanDelegate?.onScanQRCodeOpenCameraError()
            }

        })

    }

    override fun onStart() {
        super.onStart()
        binding?.zxingview?.startCamera() // 打开后置摄像头开始预览，但是并未开始识别
        binding?.zxingview?.startSpotAndShowRect() // 显示扫描框，并开始识别

    }

    override fun onStop() {
        super.onStop()
        binding?.zxingview?.stopCamera() // 关闭摄像头预览，并且隐藏扫描框


    }

    override fun onDestroy() {
        super.onDestroy()
        binding?.zxingview?.onDestroy() // 关闭摄像头预览，并且隐藏扫描框
    }


    var binding:LayoutScanBinding?=null
    override fun initViewBinding() {
         binding = LayoutScanBinding.inflate(layoutInflater)
        setContentView(binding?.root)
    }

    override fun onScanQRCodeSuccess(result: String?) {
        scanDelegate?.onScanQRCodeSuccess(result!!)
    }

    override fun onCameraAmbientBrightnessChanged(isDark: Boolean) {

        // 这里是通过修改提示文案来展示环境是否过暗的状态，接入方也可以根据 isDark 的值来实现其他交互效果
        var tipText: String? = binding?.zxingview?.scanBoxView?.tipText
        val ambientBrightnessTip = "\n环境过暗，请打开闪光灯"
        if (isDark) {
            if (!tipText!!.contains(ambientBrightnessTip)) {
                binding?.zxingview?.scanBoxView?.tipText = tipText + ambientBrightnessTip
            }
        } else {
            if (tipText!!.contains(ambientBrightnessTip)) {
                tipText = tipText.substring(0, tipText.indexOf(ambientBrightnessTip))
                binding?.zxingview?.scanBoxView?.tipText = tipText
            }
        }
    }

    override fun onScanQRCodeOpenCameraError() {

    }




}