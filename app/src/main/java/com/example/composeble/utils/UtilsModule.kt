package com.example.composeble.utils

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class, ActivityComponent::class)
object UtilsModule{
    @Provides
    fun providePermissionUtils(@ApplicationContext appContext: Context):PermissionUtils{
        return PermissionUtils(appContext)
    }
}