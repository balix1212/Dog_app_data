package com.verticalcoding.dogs.ui.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage

@Composable
fun DogDetailScreen(
    dogId: Int,
    viewModel: DogDetailViewModel = hiltViewModel()
) {
    val dog by viewModel.getDogDetails(dogId).collectAsStateWithLifecycle()
    val isLoading by viewModel.loading.collectAsStateWithLifecycle(false)

    if (isLoading) {
        CircularProgressIndicator(modifier = Modifier.fillMaxSize())
    } else {
        dog?.let {
            Column(modifier = Modifier.padding(16.dp)) {
                AsyncImage(
                    model = it.imageUrl,
                    contentDescription = "Dog image",
                    modifier = Modifier.size(128.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Name: ${it.name}", style = MaterialTheme.typography.h5)
                Text(text = "Breed: ${it.breed}", style = MaterialTheme.typography.subtitle1)
            }
        } ?: Text("Dog not found!")
    }
}
