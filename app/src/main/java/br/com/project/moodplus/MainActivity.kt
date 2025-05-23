package br.com.project.moodplus

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
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
import br.com.project.moodplus.components.CalendarScreen
import br.com.project.moodplus.components.FormScreen
import br.com.project.moodplus.components.HomeScreen
import br.com.project.moodplus.components.IntroScreen
import br.com.project.moodplus.components.MoodValidScreen
import br.com.project.moodplus.components.OrientacoesScreen
import br.com.project.moodplus.mock.MockServer
import br.com.project.moodplus.ui.theme.MoodPlusTheme
import br.com.project.moodplus.viewmodel.MoodScreenViewModel
import br.com.project.moodplus.viewmodel.OrientacoesScreenViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private val mockScope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            kotlin.runCatching {
                MoodPlusTheme {
                    Scaffold(modifier = Modifier.fillMaxSize()) {

                        val navController = rememberNavController()
                        val moodScreenViewModel: MoodScreenViewModel = viewModel()
                        val orientacoesScreenViewModel: OrientacoesScreenViewModel = viewModel()

                        NavHost(navController = navController, startDestination = "intro") {
                            composable(route = "intro") {
                                IntroScreen(navController)
                            }
                            composable(route = "MoodValid") {
                                MoodValidScreen(
                                    navController = navController,
                                    moodScreenViewModel = moodScreenViewModel
                                )
                            }
                            composable(route = "FormScreen") {
                                FormScreen(
                                    navController = navController,
                                    moodScreenViewModel = moodScreenViewModel
                                )
                            }
                            composable(route = "OrientacoesScreen") {
                                OrientacoesScreen(
                                    navController = navController,
                                    orientacoesScreenViewModel = orientacoesScreenViewModel
                                )
                            }
                            composable(route = "HomeScreen") {
                                HomeScreen(navController)
                            }
                            composable(route = "CalendarScreen") {
                                CalendarScreen(navController)
                            }
                        }
                    }
                }
            }.onFailure {
                it.printStackTrace()
            }
        }
        mockScope.launch {
            MockServer.start()
            MockServer.listaEventos()

            Log.d("MOCK_SERVER", "Mock server URL: ${MockServer.url()}")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mockScope.cancel()
        MockServer.shutdown()
    }
}
