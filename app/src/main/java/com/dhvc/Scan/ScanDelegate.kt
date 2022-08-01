package com.dhvc.Scan

interface ScanDelegate {
    fun onScanQRCodeSuccess(result:String)
    fun onScanQRCodeOpenCameraError()
}