package com.example.composeble.ui.pages

import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothManager
import android.bluetooth.le.ScanCallback
import android.bluetooth.le.ScanResult
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.composeble.MainViewModel
import com.example.composeble.routes.Routes
import com.example.composeble.utils.PermissionUtils
import logcat.LogPriority
import logcat.logcat

@Composable
fun PagesMain(
    modifier: Modifier = Modifier,
    nav: NavController,
    permissionUtils: PermissionUtils
) {
    val viewModel = hiltViewModel<MainViewModel>()
    val tvStatusBluetooth by viewModel.isBluetoothEnabled.observeAsState()
    val context = LocalContext.current
    val bluetoothManager =
        context.getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
    val bluetoothAdapter = bluetoothManager.adapter
    val bluetoothLeScanner = bluetoothAdapter.bluetoothLeScanner

    var listDevice: List<BluetoothDevice?> = listOf()

    val scanCallBack: ScanCallback = object : ScanCallback() {
        override fun onScanResult(callbackType: Int, result: ScanResult?) {
            super.onScanResult(callbackType, result)
            logcat(
                tag = "Ble",
                priority = LogPriority.ERROR
            ) { "Device name : ${result?.device?.name}" }
            listDevice.toMutableList().add(result?.device)
        }

    }


    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) {

    }

    val bluetoothLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult(),
        onResult = { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                logcat(
                    priority = LogPriority.ERROR,
                    tag = "Result Code"
                ) { "Cek result code ${result.resultCode}" }
            }
        })

    fun checkBluetooth() {
        if (permissionUtils.hasPermission()) {
            viewModel.checkBluetooth(callBack = {
                val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
                bluetoothLauncher.launch(enableBtIntent)
            }, )
        } else {
            permissionLauncher.launch(permissionUtils.listPermission())
        }
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {},
        bottomBar = {},
    ) {
        Surface(color = MaterialTheme.colors.background) {
            LazyColumn(content = {
                item {
                    Column(
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                    ) {
                        Button(onClick = { }) {
                            nav.navigate(Routes.SUB_MAIN)
                        }
                        Button(onClick = { checkBluetooth() }) {
                            Text(text = "On Bluetooth")
                        }
                        Spacer(modifier = modifier.height(10.dp))
                        Text(text = "Cek ble status : $tvStatusBluetooth")
                        Divider()
                    }
                }
                items(listDevice.size) { index ->
                    Text(text = listDevice[index]?.name ?: "ntr")

                }
            })
        }
    }
}