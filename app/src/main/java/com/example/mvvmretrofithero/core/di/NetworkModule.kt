package com.example.mvvmretrofithero.core.di

import com.example.mvvmretrofithero.showHeroDetails.data.network.HeroDetailClient
import com.example.mvvmretrofithero.showHeroes.data.network.HeroClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl("https://superheroapi.com/api/1709049216255224/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideHeroClient(retrofit: Retrofit): HeroClient {
        return retrofit.create(HeroClient::class.java)
    }

    @Singleton
    @Provides
    fun provideHeroDetailsClient(retrofit: Retrofit): HeroDetailClient {
        return retrofit.create(HeroDetailClient::class.java)
    }
}