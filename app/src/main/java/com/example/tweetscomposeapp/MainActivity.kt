package com.example.tweetscomposeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.tweetscomposeapp.api.TweetsAPI
import com.example.tweetscomposeapp.screens.CategoryScreen
import com.example.tweetscomposeapp.screens.DetailScreen
import com.example.tweetscomposeapp.ui.theme.TweetsComposeAppTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TweetsComposeAppTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(text = "TweetsCompose")
                            },
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = MaterialTheme.colorScheme.primary,
                                titleContentColor = Color.White
                            )
                        )
                    }
                ) {
                    Box(modifier = Modifier.padding(it)) {
                        App()
                    }
                }

            }
        }
    }
}

@Composable
fun App() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "category") {
        composable("category") {
            CategoryScreen {
                navController.navigate("details/${it}")
            }
        }
        composable(route = "details/{category}",
            arguments = listOf(
                navArgument("category") {
                    type = NavType.StringType
                }
            )
        ) {
            DetailScreen()
        }
    }
}