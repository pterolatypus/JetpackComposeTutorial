package com.example.jetpackcomposetutorial

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.jetpackcomposetutorial.model.data.Breed
import com.example.jetpackcomposetutorial.ui.BreedScreen
import com.example.jetpackcomposetutorial.ui.theme.CatAppTheme
import com.example.jetpackcomposetutorial.viewmodel.BreedListViewModel
import com.example.jetpackcomposetutorial.ui.BreedsListScreen
import com.example.jetpackcomposetutorial.viewmodel.BreedViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CatAppTheme {
                val navController = rememberNavController()
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    NavHost(navController, startDestination = "breedsList") {
                        composable("breedsList") {
                            BreedsListScreen() {
                                breed -> navController.navigate("breed/${breed.id}")
                            }
                        }
                        composable(
                            "breed/{breedId}",
                            arguments = listOf(navArgument("breedId") { type = NavType.StringType })
                        ) {
                            backStackEntry ->
                            BreedScreen(backStackEntry.arguments!!.getString("breedId")!!)
                        }
                    }
                }
            }
        }
    }
}