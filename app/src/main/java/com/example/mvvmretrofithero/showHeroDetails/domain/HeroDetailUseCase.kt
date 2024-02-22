package com.example.mvvmretrofithero.showHeroDetails.domain

import com.example.mvvmretrofithero.showHeroDetails.data.HeroDetailRepository
import com.example.mvvmretrofithero.showHeroDetails.data.network.response.HeroDetailResponse
import javax.inject.Inject

class HeroDetailUseCase @Inject constructor(private val heroDetailRepository: HeroDetailRepository) {

    suspend fun getDetailHeroById(idHero: Int): HeroDetailResponse {
        return heroDetailRepository.getHeroDetailById(idHero)
    }
}