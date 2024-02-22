package com.example.mvvmretrofithero.showHeroes.data.network

import com.example.mvvmretrofithero.showHeroes.data.network.response.HeroResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface HeroClient {

    @GET("search/{name}")
    suspend fun getHeroByName(@Path("name") nameHero: String): Response<HeroResponse>
}