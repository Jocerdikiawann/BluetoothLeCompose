package com.example.composeble.ui.pages

import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.composeble.MainViewModel
import com.example.composeble.utils.PermissionUtils
import kotlinx.coroutines.launch
import logcat.LogPriority
import logcat.logcat
import no.nordicsemi.android.support.v18.scanner.BluetoothLeScannerCompat
import no.nordicsemi.android.support.v18.scanner.ScanCallback
import no.nordicsemi.android.support.v18.scanner.ScanResult
import no.nordicsemi.android.support.v18.scanner.ScanSettings

@Composable
fun PagesSubMain(
    modifier: Modifier = Modifier,
    nav: NavHostController,
    permissionUtils: PermissionUtils,
) {
    val viewModel = hiltViewModel<MainViewModel>()
    val tvStatusBluetooth by viewModel.isBluetoothEnabled.observeAsState()
    val device by viewModel.listDevice.observeAsState(initial = emptyList())
    val scope = rememberCoroutineScope()

    val bluetoothLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult(),
        onResult = { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                logcat(
                    priority = LogPriority.ERROR,
                    tag = "Result Code"
                ) { "Cek result code ${result.resultCode == Activity.RESULT_OK}" }
            }
        })

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) {

    }

    fun checkPermission() {
        if (permissionUtils.hasPermission()) {
            try {
                viewModel.checkBluetooth(callBack = {
                    val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
                    bluetoothLauncher.launch(enableBtIntent)
                })
            } catch (e: Exception) {
                logcat(tag = "except", priority = LogPriority.ERROR) { e.message.toString() }
            }
        } else {
            permissionLauncher.launch(permissionUtils.listPermission())
        }
    }

    LaunchedEffect(key1 = Unit, block = {
        scope.launch {
            checkPermission()
        }
    })


    Scaffold {
        LazyColumn(content = {
            item {
                Text(text = "$tvStatusBluetooth")
            }
            items(device){
                data->
                Text(text = data.name)
            }

        })
    }
}