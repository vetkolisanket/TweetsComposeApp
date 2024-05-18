package com.example.tweetscomposeapp.di

import android.content.Context
import com.example.tweetscomposeapp.api.TweetsAPI
import com.example.tweetscomposeapp.data.TweetsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun providesTweetsRepository(api: TweetsAPI, @ApplicationContext context: Context) =
        TweetsRepository(api, context)

}