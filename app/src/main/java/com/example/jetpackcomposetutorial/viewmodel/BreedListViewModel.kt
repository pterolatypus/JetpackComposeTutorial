package com.example.jetpackcomposetutorial.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.jetpackcomposetutorial.model.data.Breed
import com.example.jetpackcomposetutorial.model.Breeds
import kotlinx.coroutines.launch

class BreedListViewModel : ViewModel()  {

    val breeds = liveData {
        emit(Breeds.getBreeds())
    }
}