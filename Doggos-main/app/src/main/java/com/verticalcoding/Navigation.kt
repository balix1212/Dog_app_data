package com.verticalcoding

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.verticalcoding.dogs.ui.dogs.DogsScreen
import com.verticalcoding.dogs.ui.detail.DogDetailScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainNavigation() {
    val navController = rememberNavController()
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        when (currentRoute) {
                            "main" -> "Dogs"
                            "details/{dogId}" -> "Dog Details"
                            else -> ""
                        }
                    )
                },
                navigationIcon = {
                    if (currentRoute == "details/{dogId}") {
                        IconButton(onClick = { navController.navigateUp() }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back"
                            )
                        }
                    }
                }
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "main",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("main") {
                DogsScreen(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    navigationController = navController
                )
            }
            composable("details/{dogId}") { backStackEntry ->
                val dogId = backStackEntry.arguments?.getString("dogId")?.toIntOrNull()
                if (dogId != null) {
                    DogDetailScreen(dogId = dogId)
                } else {
                    Text("Invalid dog ID")
                }
            }
        }
    }
}