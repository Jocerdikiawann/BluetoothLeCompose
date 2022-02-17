package com.example.composeble

import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothManager
import android.bluetooth.le.BluetoothLeScanner
import android.bluetooth.le.ScanCallback
import android.bluetooth.le.ScanResult
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.ParcelUuid
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.internal.Contexts
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import logcat.logcat
import no.nordicsemi.android.support.v18.scanner.BluetoothLeScannerCompat
import no.nordicsemi.android.support.v18.scanner.ScanFilter
import no.nordicsemi.android.support.v18.scanner.ScanSettings
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    private val bluetoothAdapter: BluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
    private val scanner: BluetoothLeScannerCompat = BluetoothLeScannerCompat.getScanner()

    private var _listDevice = MutableLiveData<List<BluetoothDevice?>>()
    val listDevice get() = _listDevice


    private var _isBluetoothEnabled = MutableLiveData<String>()
    val isBluetoothEnabled get() = _isBluetoothEnabled

    private val settings = ScanSettings.Builder()
        .setLegacy(false)
        .setScanMode(ScanSettings.SCAN_MODE_LOW_LATENCY)
        .setReportDelay(5000)
        .setUseHardwareBatchingIfSupported(true)
        .build()

    private val scanCallBack: no.nordicsemi.android.support.v18.scanner.ScanCallback =
        object : no.nordicsemi.android.support.v18.scanner.ScanCallback() {
            override fun onScanResult(
                callbackType: Int,
                result: no.nordicsemi.android.support.v18.scanner.ScanResult
            ) {
                super.onScanResult(callbackType, result)
                if (_listDevice.value?.isEmpty() == true) {
                    _listDevice.value!!.toMutableList().add(result.device)
                    logcat { "is add : ${result.device.name}" }
                }
                val exist = _listDevice.value?.contains(result.device)
                if (exist == false) {
                    logcat { "is exist ${result.device.name}" }
                    _listDevice.value!!.toMutableList().add(result.device)
                }
            }
        }


    fun checkBluetooth(callBack: () -> Unit) = viewModelScope.launch {
        if (!bluetoothAdapter.isEnabled) {
            _isBluetoothEnabled.value = bluetoothAdapter.isEnabled.toString()
            callBack()
        }
        if (bluetoothAdapter.isEnabled) {
            _isBluetoothEnabled.value = bluetoothAdapter.isEnabled.toString()
            scanner.startScan(null, settings, scanCallBack)
        }
    }


}
