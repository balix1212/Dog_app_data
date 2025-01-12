package com.verticalcoding.dogs.ui.dogs

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import com.verticalcoding.dogs.data.models.Dog

@Composable
fun DogsScreen(
    modifier: Modifier = Modifier,
    navigationController: NavHostController
) {
    val dogs = listOf(
        Dog(id = 1, name = "Buddy", breed = "Golden Retriever", imageUrl = ""),
        Dog(id = 2, name = "Max", breed = "Labrador", imageUrl = "")
    ) 

    LazyColumn(modifier = modifier) {
        items(dogs) { dog ->
            DogItem(dog = dog, navController = navigationController)
        }
    }
}

@Composable
fun DogItem(dog: Dog, navController: NavHostController) {
    Row(modifier = Modifier
        .clickable { navController.navigate("details/${dog.id}") }
        .padding(16.dp)
    ) {
        Text(text = dog.name)
    }
}
