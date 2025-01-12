package com.verticalcoding.dogs.data.network

import retrofit2.http.GET

interface DogApi {
    @GET("breeds/image/random/10")
    suspend fun getRandomDogs(): DogResponse
}

data class DogResponse(
    val message: List<String>,
    val status: String
)
