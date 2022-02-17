object Version {
    const val KotlinAndroidVersion = "1.5.31"
    const val KotlinVersion = "1.6.0"
    const val applicationId = "com.example.composeble"
}

object Libs {

    //google
    object Com {
        object Google {
            //accompanist(external library for jetpack compose)
            object Accompanist {
                val accompanistSystemUiController = "com.google.accompanist:accompanist-systemuicontroller:0.24.1-alpha"
            }

            //dagger hilt
            object Dagger {
                private const val dagger_hilt_version = "2.38.1"
                val hiltAndroid = "com.google.dagger:hilt-android:$dagger_hilt_version"
                val hiltAndroidCompiler ="com.google.dagger:hilt-android-compiler:$dagger_hilt_version"
                val hiltAndroidTesting ="com.google.dagger:hilt-android-testing:$dagger_hilt_version"
            }

            object Truth {
                val truth ="com.google.truth:truth:1.1"
            }
        }

        object Squareup {
            //for logging in debugging mode
            object Logcat {
                val logcat ="com.squareup.logcat:logcat:0.1"

            }
        }
    }

    //androidx
    object AndroidX {
        object Multidex {
            val multidex = "androidx.multidex:multidex:2.0.1"
        }

        object Ble{
            val bleScanner = "no.nordicsemi.android.support.v18:scanner:1.6.0"
        }

        object Core {
            val coreKtx ="androidx.core:core-ktx:1.7.0"
        }

        object Compose {
            object Ui {
                val ui ="androidx.compose.ui:ui:1.2.0-alpha02"
                val uiToolingPreview ="androidx.compose.ui:ui-tooling-preview:1.2.0-alpha02"
                val uiTooling ="androidx.compose.ui:ui-tooling:1.2.0-alpha02"
                val uiTestJunit4 ="androidx.compose.ui:ui-test-junit4:1.2.0-alpha02"
                val uiTestManifest ="androidx.compose.ui:ui-test-manifest:1.2.0-alpha02"
            }

            object Runtime {
                val runtimeLivedata="androidx.compose.runtime:runtime-livedata:1.2.0-alpha02"
            }

            object Material {
                val material ="androidx.compose.material:material:1.2.0-alpha02"
            }
        }

        object Lifecycle {
            private const val lifecycle_version = "2.5.0-alpha01"

            val lifecycleRuntimeKtx ="androidx.lifecycle:lifecycle-runtime-ktx:2.4.0"
            val lifecycleViewmodel ="androidx.lifecycle:lifecycle-viewmodel:$lifecycle_version"
            val lifecycleLivedata ="androidx.lifecycle:lifecycle-livedata:$lifecycle_version"
            val lifecycleRuntime ="androidx.lifecycle:lifecycle-runtime:$lifecycle_version"
            val lifecycleViewmodelSavedstate ="androidx.lifecycle:lifecycle-viewmodel-savedstate:$lifecycle_version"

        }

        object Activity {
            val activityCompose ="androidx.activity:activity-compose:1.4.0"
        }

        object Arch {
            const val arch_version = "2.1.0"

            object Core {
                val coreTesting ="androidx.arch.core:core-testing:$arch_version"
            }
        }

        object Navigation {
            val navigationCompose ="androidx.navigation:navigation-compose:2.5.0-alpha01"
        }

        object Hilt {
            val hiltNavigationCompose ="androidx.hilt:hilt-navigation-compose:1.0.0"
        }

        object Room {
            private const val room_version = "2.4.1"
            val roomRuntime ="androidx.room:room-runtime:$room_version"
            val roomCompiler ="androidx.room:room-compiler:$room_version"
            val roomKtx ="androidx.room:room-ktx:$room_version"
            val roomTesting ="androidx.room:room-testing:$room_version"
            val roomPaging ="androidx.room:room-paging:$room_version"
        }

        object Test {
            object Ext {
                val junit ="androidx.test.ext:junit:1.1.3"
            }

            object Espresso {
                val espressoCore ="androidx.test.espresso:espresso-core:3.4.0"
            }
        }
    }

    object Org {
        object Jetbrains {
            //for use `await()` with google and firebase Task API
            object Kotlinx {
                val googlePlayKotlinCoroutine ="org.jetbrains.kotlinx:kotlinx-coroutines-play-services:${Version.KotlinVersion}"
                val kotlinxCoroutinesTest ="org.jetbrains.kotlinx:kotlinx-coroutines-test:${Version.KotlinVersion}"
            }
        }

        object Mockito {
            val mockitoCore ="org.mockito:mockito-core:4.0.0"
        }

        object Robolectric {
            val robolectric ="org.robolectric:robolectric:4.5.1"
        }
    }

    object Br {
        object Com {
            object Devsrsouza {
                object Compose {
                    object Icons {
                        object Android {
                            val octicons ="br.com.devsrsouza.compose.icons.android:octicons:1.0.0"
                            val feather ="br.com.devsrsouza.compose.icons.android:feather:1.0.0"
                        }
                    }
                }
            }
        }
    }

    object JodaTime {
        val jodaTime ="joda-time:joda-time:2.10.13"
    }

    object Io {
        object CoilKt {
            val coilCompose ="io.coil-kt:coil-compose:1.4.0"
        }
    }

    object Junit {
        val junit ="junit:junit:4.13.2"
    }

}