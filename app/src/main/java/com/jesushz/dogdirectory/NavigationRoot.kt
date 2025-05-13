package com.jesushz.dogdirectory

import androidx.activity.compose.LocalActivity
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.jesushz.dogdirectory.core.util.Routes
import com.jesushz.dogdirectory.dog.presentation.dog_list.DogListScreenRoot

@Composable
fun NavigationRoot(
    navController: NavHostController,
    startDestination: Routes = Routes.DogsGraph
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        dogsGraph(navController)
    }
}

private fun NavGraphBuilder.dogsGraph(
    navController: NavHostController
) {
    navigation<Routes.DogsGraph>(
        startDestination = Routes.DogListScreen
    ) {
        composable<Routes.DogListScreen> {
            val activity = LocalActivity.current
            DogListScreenRoot(
                onExit = {
                    activity?.finish()
                }
            )
        }
    }
}
