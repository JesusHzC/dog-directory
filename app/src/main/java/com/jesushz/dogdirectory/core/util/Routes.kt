package com.jesushz.dogdirectory.core.util

import kotlinx.serialization.Serializable

sealed interface Routes {

    // Graphs
    @Serializable
    data object DogsGraph: Routes

    // Screens
    @Serializable
    data object DogListScreen: Routes

}
