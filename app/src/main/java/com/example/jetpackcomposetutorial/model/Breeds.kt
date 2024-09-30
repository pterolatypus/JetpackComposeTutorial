package com.example.jetpackcomposetutorial.model

import com.example.jetpackcomposetutorial.api.ApiInstance
import com.example.jetpackcomposetutorial.model.data.Breed
import retrofit2.HttpException

object Breeds {
    private val breedService = ApiInstance.breedService

    private var _breeds: List<Breed> = emptyList()
    private var _breedsMap: Map<String, Breed> = emptyMap()
    private var _lastUpdated = 0L
    private val _cacheLifetime = 60000L

    suspend fun getBreeds(): List<Breed> {
        reloadBreeds()
        return _breeds
    }

    private fun isUpToDate(): Boolean {
        return (_breeds.isNotEmpty() && System.currentTimeMillis() < _lastUpdated + _cacheLifetime)
    }

    private suspend fun reloadBreeds() {
        if (isUpToDate()) return
        try {
            _breeds = breedService.getBreeds()
        } catch (e: HttpException) {
            e.printStackTrace()
        }
        _breedsMap = _breeds.associateBy { breed -> breed.id }
        _lastUpdated = System.currentTimeMillis()
    }

    suspend fun getBreed(id: String): Breed? {
        reloadBreeds()
        return _breedsMap[id]
    }
}