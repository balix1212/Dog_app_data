package com.verticalcoding.dogs.data.models

data class Dog(
    val id: Int,
    val name: String,
    val breed: String,
    val imageUrl: String,
    val isFav: Boolean = false
)
