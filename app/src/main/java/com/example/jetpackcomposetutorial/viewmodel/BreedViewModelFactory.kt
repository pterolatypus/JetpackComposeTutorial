package com.example.jetpackcomposetutorial.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class BreedViewModelFactory(private val breedId: String): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T = BreedViewModel(breedId) as T
}