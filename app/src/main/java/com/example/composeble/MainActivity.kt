package com.example.composeble

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.composeble.routes.Routes
import com.example.composeble.ui.pages.PagesMain
import com.example.composeble.ui.pages.PagesSubMain
import com.example.composeble.ui.theme.ComposeBleTheme
import com.example.composeble.utils.PermissionUtils
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var permissionUtils: PermissionUtils

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navHostController = rememberNavController()
            ComposeBleTheme {
                // A surface container using the 'background' color from the theme
                NavHost(navController = navHostController, startDestination = Routes.SUB_MAIN) {
                    composable(Routes.MAIN) {
                        PagesMain(nav = navHostController, permissionUtils = permissionUtils)
                    }
                    composable(Routes.SUB_MAIN) {
                        PagesSubMain(nav = navHostController,permissionUtils = permissionUtils)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeBleTheme {

    }
}