package com.verticalcoding.dogs.data

import com.verticalcoding.dogs.data.local.database.DogEntity
import com.verticalcoding.dogs.data.local.database.DogEntityDao
import com.verticalcoding.dogs.data.network.DogApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface DogRepository {
    val dogs: Flow<List<Dog>>
    suspend fun add(name: String)
    suspend fun remove(id: Int)
    suspend fun triggerFav(id: Int)
    suspend fun fetchDogsFromApi()
}

class DefaultDogRepository @Inject constructor(
    private val dogDao: DogEntityDao,
    private val dogApi: DogApi
) : DogRepository {

    override val dogs: Flow<List<Dog>> = dogDao.getSortedDogs().map { items ->
        items.map {
            Dog(
                id = it.uid,
                name = it.name,
                breed = "Jack Russel Terrier",
                imageUrl = it.imageUrl,
                isFav = it.isFav
            )
        }
    }

    override suspend fun add(name: String) {
        dogDao.insertDog(DogEntity(name = name, isFav = false, imageUrl = ""))
    }

    override suspend fun remove(id: Int) {
        dogDao.removeDog(id)
    }

    override suspend fun triggerFav(id: Int) {
        dogDao.triggerFavDog(id)
    }

    override suspend fun fetchDogsFromApi() {
        val response = dogApi.getRandomDogs()
        if (response.status == "success") {
            response.message.forEach { imageUrl ->
                dogDao.insertDog(DogEntity(name = "Dog", isFav = false, imageUrl = imageUrl))
            }
        }
    }
}
