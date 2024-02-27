package com.example.mvvmretrofithero.showHeroes.data

import com.example.mvvmretrofithero.showHeroes.data.network.HeroService
import com.example.mvvmretrofithero.showHeroes.data.network.response.HeroResponse
import javax.inject.Inject

class HeroRepository @Inject constructor(private val api: HeroService) {
    suspend fun getListHeroByName(nameHero: String): HeroResponse {
        return api.getListSuperHero(nameHero)
    }
}