package com.example.mvvmretrofithero.showHeroDetails.data.network

import com.example.mvvmretrofithero.showHeroDetails.data.network.response.HeroDetailResponse
import javax.inject.Inject

class HeroDetailService @Inject constructor(private val heroDetailClient: HeroDetailClient) {
    suspend fun getHeroDetailById(idHero: Int): HeroDetailResponse {
        return heroDetailClient.getDetailHeroById(idHero)
    }
}