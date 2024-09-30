package com.example.jetpackcomposetutorial.api

import com.example.jetpackcomposetutorial.model.data.Breed
import retrofit2.http.GET
import retrofit2.http.Query

interface BreedService {
    @GET("breeds")
    suspend fun getBreeds(): List<Breed>
}