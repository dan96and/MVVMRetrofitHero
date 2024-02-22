package com.example.mvvmretrofithero.showHeroes.data.network.response

data class HeroResponse(
    val response: String,
    val results: List<SuperHero>
)

data class SuperHero(
    val id: Int,
    val name: String,
    val image: Image
)

data class Image(
    val url: String
)