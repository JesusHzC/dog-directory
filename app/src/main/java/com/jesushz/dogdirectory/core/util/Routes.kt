package com.jesushz.dogdirectory.core.util

import kotlinx.serialization.Serializable

sealed interface Routes {

    // Graphs
    @Serializable
    data object DogsGraph: Routes

    // Screens
    @Serializable
    data object DogListScreen: Routes
    @Serializable
    data class DogDetailScreen(
        val name: String,
        val description: String,
        val age: Int,
        val image: String
    ): Routes

}
