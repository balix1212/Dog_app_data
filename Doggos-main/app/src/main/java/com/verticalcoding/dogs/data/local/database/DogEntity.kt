package com.verticalcoding.dogs.data.local.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dogs")
data class DogEntity(
    val name: String,
    val imageUrl: String,
    @ColumnInfo(defaultValue = "0")
    val isFav: Boolean
) {
    @PrimaryKey(autoGenerate = true)
    var uid: Int = 0
}