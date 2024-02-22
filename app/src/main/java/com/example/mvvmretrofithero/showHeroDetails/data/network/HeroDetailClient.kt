package com.example.mvvmretrofithero.showHeroDetails.data.network

import com.example.mvvmretrofithero.showHeroDetails.data.network.response.HeroDetailResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface HeroDetailClient {
    @GET("{id}")
    suspend fun getDetailHeroById(@Path("id") idHero: Int): HeroDetailResponse
}