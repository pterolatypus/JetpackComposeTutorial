package com.example.jetpackcomposetutorial.model

import com.example.jetpackcomposetutorial.api.ApiInstance.imageService
import com.example.jetpackcomposetutorial.model.data.Image

object Images {

    private var _images = mutableMapOf<String, ImageList>()

    suspend fun getImages(breedId: String): List<Image> {
        if (!_images.containsKey(breedId)) _images[breedId] = ImageList(breedId)
        return _images[breedId]!!.getImages()
    }

    class ImageList(private val breedId: String){

        private var _images: List<Image> = emptyList()
        private var _lastUpdated = 0L
        private val _cacheLifetime = 60000L

        fun isUpToDate(): Boolean {
            return (_images.isNotEmpty() && System.currentTimeMillis() < _lastUpdated+_cacheLifetime)
        }

        private suspend fun reloadImages() {
            _images = imageService.getImages(breedId, 5)
        }

        suspend fun getImages(): List<Image> {
            if (!isUpToDate()) reloadImages()
            return _images
        }
    }
}
