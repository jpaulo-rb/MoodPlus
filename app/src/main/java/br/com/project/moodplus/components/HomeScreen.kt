package br.com.project.moodplus.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import br.com.project.moodplus.R
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController

@Composable
fun HomeScreen(navController: NavController) {
    Box(modifier= Modifier.background(colorResource(id = R.color.lightBlue)).fillMaxSize()){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 50.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo"
            )
            // Row que segura o texto do mood
            Row(modifier = Modifier.fillMaxWidth().padding(start = 17.dp)) {
                Text(
                    modifier = Modifier.padding(top = 10.dp),
                    text = "Seu mood de hoje",
                    textAlign = TextAlign.Left,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.width(5.dp))
                Image(
                    painter = painterResource(id = R.drawable.sad),
                    contentDescription = "Sad mood"
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Row que segura os cards
            Row(
                modifier = Modifier
                    .background(colorResource(id = R.color.Dark))
                    .border(2.dp, colorResource(id = R.color.Yellow))
                    .width(345.dp)
                    .height(320.dp)
                    .padding(20.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(modifier= Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally){
                    Text(
                        text = "Teste",
                        color = colorResource(id = R.color.White),
                        fontWeight = FontWeight.SemiBold
                    )
                }
                // Box para colocar a linha
                Box(
                    modifier = Modifier
                        .width(1.dp)
                        .fillMaxHeight()
                        .background(colorResource(id = R.color.Yellow))
                )
                Column(modifier= Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "Teste",
                        color = colorResource(id = R.color.White),
                        fontWeight = FontWeight.SemiBold
                    )
                }
                // Box para colocar a linha
                Box(
                    modifier = Modifier
                        .width(1.dp)
                        .fillMaxHeight()
                        .background(colorResource(id = R.color.Yellow))
                )
                Column(modifier= Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "Teste",
                        color = colorResource(id = R.color.White),
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }


        }

    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreenPreview(){
    HomeScreen(NavHostController(LocalContext.current))
}