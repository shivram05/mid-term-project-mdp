package com.miu.edu.sports.news.information.di

import android.content.Context
import com.miu.edu.sports.news.information.utils.PreferenceStorage
import com.miu.edu.sports.news.information.utils.SharedPreferenceStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providesPreferenceStorage(context: Context): PreferenceStorage = SharedPreferenceStorage(context)
}