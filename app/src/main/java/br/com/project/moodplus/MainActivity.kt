package br.com.project.moodplus

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.project.moodplus.components.FormScreen
import br.com.project.moodplus.components.IntroScreen
import br.com.project.moodplus.components.MoodValidScreen
import br.com.project.moodplus.ui.theme.MoodPlusTheme
import br.com.project.moodplus.viewmodel.MoodScreenViewModel


class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            kotlin.runCatching {
                MoodPlusTheme {
                    Scaffold(modifier = Modifier.fillMaxSize()) {

                        val navController = rememberNavController()
                        val moodScreenViewModel: MoodScreenViewModel = viewModel()

                        NavHost(navController = navController, startDestination = "intro"){
                            composable(route = "intro"){
                                IntroScreen(navController)
                            }
                            composable(route = "MoodValid"){
                                MoodValidScreen(
                                    navController = navController,
                                    moodScreenViewModel = moodScreenViewModel
                                )
                            }
                            composable(route = "FormScreen"){
                                FormScreen(
                                    navController = navController,
                                    moodScreenViewModel = moodScreenViewModel
                                    )
                            }
                        }
                    }
                }
            }.onFailure {
                it.printStackTrace()
        }
    }
}
}
