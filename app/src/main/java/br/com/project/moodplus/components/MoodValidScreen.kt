package br.com.project.moodplus.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import br.com.project.moodplus.R

@Composable
fun MoodValidScreen(navController: NavController){
    Box(modifier = Modifier.background(colorResource(id = R.color.lightBlue)).fillMaxSize()){
        Column(
            modifier = Modifier.fillMaxWidth().padding(top = 50.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center

        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo"
            )

            Text(
                modifier = Modifier.size(327.dp, 78.dp),
                text = "Nos conte como você esta hoje",
                textAlign = TextAlign.Center,
                color = colorResource(id = R.color.Dark),
                fontSize = 32.sp,
                fontWeight = FontWeight.SemiBold
            )
            Row(
                modifier = Modifier
                    .padding(top = 50.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Image(
                    modifier = Modifier.size(80.dp, 80.dp)
                        .clickable { navController.navigate("FormScreen") },
                    painter = painterResource(id = R.drawable.happy),
                    contentDescription = "Happy Face"
                )

                Image(
                    modifier = Modifier.size(75.dp, 75.dp)
                        .clickable { navController.navigate("FormScreen") },
                    painter = painterResource(id = R.drawable.neutral),
                    contentDescription = "Neutral Face"
                )

                Image(
                    modifier = Modifier.size(80.dp, 80.dp)
                        .clickable { navController.navigate("FormScreen") },
                    painter = painterResource(id = R.drawable.sad),
                    contentDescription = "Sad Face"
                )
            }



        }
    }
}

//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun MoodValidScreenPreview(){
//    MoodValidScreen(NavHostController(LocalContext.current))
//}