package com.example.jetpackcomposetutorial.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.jetpackcomposetutorial.model.data.Breed
import com.example.jetpackcomposetutorial.viewmodel.BreedListViewModel

@Composable
fun BreedsListScreen(breedListViewModel: BreedListViewModel = viewModel(), onSelectBreed: (Breed) -> Unit) {
    val breeds by breedListViewModel.breeds.observeAsState(emptyList())

    if (breeds.isEmpty()) {
        LoadingWheel("Loading list of breeds...")
    } else {
        BreedsList(breeds, onSelectBreed)
    }
}

@Composable
fun BreedsList(breeds: List<Breed>, onSelectBreed: (Breed) -> Unit) {
    LazyColumn {
        items(breeds) { breed ->
            BreedEntry(breed, onSelectBreed)
            Spacer(modifier = Modifier.height(4.dp))
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun BreedEntry(breed: Breed, onSelectBreed: (Breed) -> Unit) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onSelectBreed(breed)
            },
        shadowElevation = 1.dp
    ) {
        Row {
            GlideImage(model = breed.image?.url, contentDescription = "${breed.name} reference image") {
                requestBuilder -> requestBuilder.override(200).circleCrop()
            }
            Text(text = breed.name)
        }
    }
}