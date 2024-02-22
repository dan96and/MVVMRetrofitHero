package com.example.mvvmretrofithero.showHeroes.domain

import com.example.mvvmretrofithero.showHeroes.data.HeroRepository
import com.example.mvvmretrofithero.showHeroes.data.network.response.HeroResponse
import javax.inject.Inject

class HeroUseCase @Inject constructor(private val repository: HeroRepository) {

    suspend fun getListHeroByName(nameHero: String): HeroResponse {
        return repository.getListHeroByName(nameHero)
    }
}