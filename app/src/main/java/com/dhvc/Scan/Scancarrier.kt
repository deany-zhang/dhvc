package com.dhvc.Scan

import android.util.Log
import android.widget.Toast
import com.dhvc.MainActivity

class Scancarrier :ScanDelegate{
    private lateinit var ScanData: ScanSuccessdata
    fun setScan(scamdata: ScanSuccessdata){
        ScanData = scamdata
    }

     override fun onScanQRCodeSuccess(result: String) {
         Log.e("TAG", "onScanQRCodeSuccess: $result")
         ScanData.ScanSuccessData(result)
        init21(result)
     }

    private fun init21(result: String) {
        ScanData.ScanSuccessData(result)
    }

    override fun onScanQRCodeOpenCameraError() {
         ScanData?.ScanError()
     }


}
