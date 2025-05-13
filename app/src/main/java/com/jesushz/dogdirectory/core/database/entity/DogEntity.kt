@file:OptIn(ExperimentalUuidApi::class)

package com.jesushz.dogdirectory.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@Entity
data class DogEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String = Uuid.random().toString(),
    val name: String,
    val description: String,
    val age: Int,
    val image: String
)
