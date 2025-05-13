@file:OptIn(ExperimentalUuidApi::class)

package com.jesushz.dogdirectory.dog.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@Serializable
data class Dog(
    @SerialName("dogName") val name: String,
    val description: String,
    val age: Int,
    val image: String,
    val id: String = Uuid.random().toString()
)
