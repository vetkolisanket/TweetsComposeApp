package com.example.tweetscomposeapp.di

import com.example.tweetscomposeapp.api.TweetsAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun providesRetrofit(okHttpClient: OkHttpClient) = Retrofit
        .Builder()
        .baseUrl("https://api.jsonbin.io/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    @Singleton
    @Provides
    fun providesTweetsAPI(retrofit: Retrofit): TweetsAPI = retrofit.create(TweetsAPI::class.java)

    @Singleton
    @Provides
    fun providesOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }).build()

}