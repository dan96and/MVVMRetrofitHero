package com.example.mvvmretrofithero.showHeroes.data.network

import com.example.mvvmretrofithero.showHeroes.data.network.response.HeroResponse
import retrofit2.Response
import javax.inject.Inject

class HeroService @Inject constructor(private val heroClient: HeroClient) {

    suspend fun getListSuperHero(name: String): HeroResponse {

        val response: Response<HeroResponse> = heroClient.getHeroByName(name)
        return response.body() ?: throw Exception("No se pudo obtener la respuesta")

    }
}