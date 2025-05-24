package br.com.project.moodplus.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.project.moodplus.R
import br.com.project.moodplus.viewmodel.MoodScreenViewModel
import java.time.LocalDate
import androidx.compose.foundation.lazy.grid.items


@Composable
fun CalendarScreen(
    navController: NavController,
    moodScreenViewModel: MoodScreenViewModel
) {

    val primeiroDia = LocalDate.now().withDayOfMonth(1)
    val ultimoDia = LocalDate.now().withDayOfMonth(LocalDate.now().lengthOfMonth())

    val diasDoMes = (1..ultimoDia.dayOfMonth).map { dia ->
        primeiroDia.withDayOfMonth(dia)
    }

    val moods = moodScreenViewModel.buscarPorPeriodo(primeiroDia, ultimoDia)

    fun corDoMood(mood: String?): Color = when (mood) {
        "Happy" -> Color(0xFF81C784)   // Verde
        "Neutral" -> Color(0xFFFFF176) // Amarelo
        "Sad" -> Color(0xFFE57373)     // Vermelho
        else -> Color.Gray        // Sem mood
    }

    Box(modifier= Modifier.background(colorResource(id = R.color.lightBlue)).fillMaxSize()){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 50.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row(modifier = Modifier.fillMaxWidth().padding(start = 10.dp, end = 10.dp).height(200.dp)) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Logo",
                    contentScale = ContentScale.Crop
                )
            }
            LazyVerticalGrid(
                columns = GridCells.Fixed(7),
                modifier = Modifier.fillMaxWidth().padding(8.dp),
                contentPadding = PaddingValues(4.dp)
            ) {
                items(diasDoMes) { data ->
                    val moodDoDia = moods?.find { LocalDate.parse(it.data) == data }?.mood
                    val cor = corDoMood(moodDoDia)

                    Box(
                        modifier = Modifier
                            .aspectRatio(1f)
                            .padding(4.dp)
                            .background(cor, shape = RoundedCornerShape(6.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = data.dayOfMonth.toString(),
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                    }
                }
            }

        }
    }
}

//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun CalendarScreenPreview(){
//    CalendarScreen(navController = NavController(LocalContext.current))
//}