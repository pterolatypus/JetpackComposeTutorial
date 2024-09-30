package com.example.jetpackcomposetutorial.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.Placeholder
import com.bumptech.glide.integration.compose.placeholder
import com.example.jetpackcomposetutorial.model.data.Breed
import com.example.jetpackcomposetutorial.model.data.Image
import com.example.jetpackcomposetutorial.viewmodel.BreedViewModel
import com.example.jetpackcomposetutorial.viewmodel.BreedViewModelFactory

@Composable
fun BreedScreen(breedId: String, breedViewModel: BreedViewModel = viewModel(factory=BreedViewModelFactory(breedId))) {
    val breed by breedViewModel.breed.observeAsState()
    val images by breedViewModel.images.observeAsState(emptyList())

    Breed(breed, images)
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun Breed(breed: Breed?, images: List<Image>) {
    if (breed == null) NoBreed()
    else {
        LazyColumn {
            items(1) {
                Text(breed.name)
            }
            items(images) {
                img -> GlideImage(model = img.url, contentDescription = img.url)
            }
        }
    }
}

@Composable
fun NoBreed() {
    Text("The selected breed was not found!")
}