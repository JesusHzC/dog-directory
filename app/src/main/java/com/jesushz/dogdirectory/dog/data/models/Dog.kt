package com.jesushz.dogdirectory.dog.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Dog(
    @SerialName("dogName") val name: String,
    val description: String,
    val age: Int,
    val image: String
)
