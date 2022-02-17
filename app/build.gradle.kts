plugins {
    id("com.android.application")
    id("kotlin-android")
    id("dagger.hilt.android.plugin")
    kotlin("android")
    kotlin("kapt")
}

android {
    compileSdk =31

    defaultConfig {
        applicationId =Version.applicationId
        minSdk =21
        targetSdk =31
        versionCode =1
        versionName ="1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled=false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility =JavaVersion.VERSION_1_8
        targetCompatibility =JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose =true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.2.0-alpha02"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(Libs.AndroidX.Multidex.multidex)
    implementation(Libs.AndroidX.Core.coreKtx)
    implementation(Libs.AndroidX.Activity.activityCompose)
    implementation(Libs.AndroidX.Ble.bleScanner)
    with(Libs.AndroidX.Compose){
        implementation(Libs.AndroidX.Compose.Ui.ui)
        // https://stackoverflow.com/questions/68224361/jetpack-compose-cant-preview-after-updating-to-1-0-0-rc01
        implementation(Libs.AndroidX.Compose.Ui.uiTooling)
        implementation(Libs.AndroidX.Compose.Ui.uiToolingPreview)
        implementation(Libs.AndroidX.Compose.Material.material)
        implementation(Libs.AndroidX.Compose.Runtime.runtimeLivedata)
        androidTestImplementation(Libs.AndroidX.Compose.Ui.uiTestJunit4)
        debugImplementation(Libs.AndroidX.Compose.Ui.uiTestManifest)
        debugImplementation(Libs.AndroidX.Compose.Ui.uiTooling)

    }
    with(Libs.AndroidX.Lifecycle){
        implementation(lifecycleRuntimeKtx)
        // ViewModel
        implementation(lifecycleViewmodel)
        implementation(lifecycleLivedata)
        implementation(lifecycleRuntime)
        implementation(lifecycleViewmodelSavedstate)
    }
    testImplementation(Libs.AndroidX.Arch.Core.coreTesting)
    androidTestImplementation(Libs.AndroidX.Arch.Core.coreTesting)

    //hilt dagger (support viewModel)
    //https://developer.android.com/training/dependency-injection/hilt-android#setup
    //https://developer.android.com/training/dependency-injection/hilt-testing?hl=id
    with(Libs.Com.Google.Dagger){
        implementation(hiltAndroid)
        kapt(hiltAndroidCompiler)
        testImplementation(hiltAndroidTesting)
        kaptTest(hiltAndroidCompiler)
        androidTestImplementation(hiltAndroidTesting)
        kaptAndroidTest(hiltAndroidCompiler)

    }

    testImplementation(Libs.Org.Mockito.mockitoCore)
    //  Bump to 4.6.* after https://github.com/robolectric/robolectric/issues/6593 is fixed
    testImplementation(Libs.Org.Robolectric.robolectric)

    //navigation
    //https://developer.android.com/jetpack/compose/navigation
    implementation(Libs.AndroidX.Navigation.navigationCompose)

    //supaya bisa inject viewModel ke navigation
    //https://developer.android.com/jetpack/compose/libraries#hilt
    implementation(Libs.AndroidX.Hilt.hiltNavigationCompose)

    with(Libs.AndroidX.Room){
        implementation(roomRuntime)
        implementation(roomPaging)
        implementation(roomKtx)
        annotationProcessor(roomCompiler)
        kapt(roomCompiler)
        testImplementation(roomTesting)

    }

    //allow use await() in firebase task
    with(Libs.Org.Jetbrains.Kotlinx){
        implementation(googlePlayKotlinCoroutine)
    }

    //logcat
    with(Libs.Com.Squareup.Logcat){
        implementation(logcat)
    }

    //icon
    with(Libs.Br.Com.Devsrsouza.Compose.Icons.Android){
        implementation(octicons)
        implementation(feather)
    }

    //datetime
    with(Libs.JodaTime){
        implementation(jodaTime)
    }

    //system ui controller
    with(Libs.Com.Google.Accompanist){
        implementation(accompanistSystemUiController)
    }
    //image laoder
    with(Libs.Io.CoilKt){
        implementation(coilCompose)
    }


//    local unit test
    testImplementation(Libs.Junit.junit)
    testImplementation(Libs.Com.Google.Truth.truth)
    androidTestImplementation(Libs.Com.Google.Truth.truth)
//    instrumentation test
    androidTestImplementation(Libs.AndroidX.Test.Ext.junit)
    androidTestImplementation(Libs.AndroidX.Test.Espresso.espressoCore)

    androidTestImplementation(Libs.Org.Jetbrains.Kotlinx.kotlinxCoroutinesTest)

//    implementation 'androidx.core:core-ktx:1.7.0'
//    implementation 'androidx.appcompat:appcompat:1.4.0'
//    implementation 'com.google.android.material:material:1.4.0'
//    implementation "androidx.compose.ui:ui:$compose_version"
//    implementation "androidx.compose.material:material:$compose_version"
//    implementation "androidx.compose.ui:ui-tooling-preview:$compose_version"
//    implementation 'androidx.lifecycle:lifecycle-runtime-ktx:2.4.0'
//    implementation 'androidx.activity:activity-compose:1.4.0'
//    testImplementation 'junit:junit:4.+'
//    androidTestImplementation 'androidx.test.ext:junit:1.1.3'
//    androidTestImplementation 'androidx.test.espresso:espresso-core:3.4.0'
//    androidTestImplementation "androidx.compose.ui:ui-test-junit4:$compose_version"
//    debugImplementation "androidx.compose.ui:ui-tooling:$compose_version"
}