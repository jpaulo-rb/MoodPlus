package br.com.project.moodplus.components

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext

import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
    import br.com.project.moodplus.R


@Composable
fun IntroScreen(navController: NavController) {
    Box(modifier = Modifier.background(colorResource(id = R.color.lightBlue)).fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(top = 50.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo"
            )

            Button(
                onClick = { navController.navigate("MoodValid") },
                modifier = Modifier.padding(top = 40.dp).size(220.dp, 90.dp).shadow(5.dp, shape = RoundedCornerShape(20.dp)),
                colors = ButtonDefaults.buttonColors(colorResource(id = R.color.Yellow)),
                shape = RoundedCornerShape(20.dp)
            ) {
                Text(
                    text = "Entrar",
                    color = colorResource(id = R.color.Dark),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}


//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun IntroScreenPreview(){
//    IntroScreen(navController = NavHostController(LocalContext.current))
//}