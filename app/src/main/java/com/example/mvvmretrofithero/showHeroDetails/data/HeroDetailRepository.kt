package com.example.mvvmretrofithero.showHeroDetails.data

import com.example.mvvmretrofithero.showHeroDetails.data.network.HeroDetailService
import com.example.mvvmretrofithero.showHeroDetails.data.network.response.HeroDetailResponse
import javax.inject.Inject

class HeroDetailRepository @Inject constructor(private val heroDetailService: HeroDetailService) {

    suspend fun getHeroDetailById(idHero: Int): HeroDetailResponse {
        return heroDetailService.getHeroDetailById(idHero)
    }
}