package com.example.jetpackcomposetutorial.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.jetpackcomposetutorial.model.Breeds
import com.example.jetpackcomposetutorial.model.Images
import com.example.jetpackcomposetutorial.model.data.Breed
import kotlinx.coroutines.launch

class BreedViewModel(private val _breedId: String): ViewModel() {

    val breed = liveData {
        emit(Breeds.getBreed(_breedId))
    }

    val images = liveData {
        emit(Images.getImages(_breedId))
    }
}