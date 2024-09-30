package com.example.jetpackcomposetutorial.api

import com.example.jetpackcomposetutorial.model.data.Image
import retrofit2.http.GET
import retrofit2.http.Query

interface ImageService {
    @GET("images/search")
    suspend fun getImages(@Query("breed_ids") breedId : String, @Query("limit") limit: Int): List<Image>
}