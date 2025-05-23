package br.com.project.moodplus.components

import android.app.Application
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.project.moodplus.R
import br.com.project.moodplus.model.Evento
import br.com.project.moodplus.service.buscarEventos
import br.com.project.moodplus.viewmodel.OrientacoesScreenViewModel

@Composable
fun GuidenceScreen(navController: NavController, orientacoesScreenViewModel: OrientacoesScreenViewModel) {

    val eventos by orientacoesScreenViewModel.eventos.observeAsState(initial = emptyList())

    Box(modifier= Modifier.background(colorResource(id = R.color.lightBlue)).fillMaxSize()) {
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

            Button(
                onClick = { buscarEventos(orientacoes = orientacoesScreenViewModel) },
                modifier = Modifier.padding(top = 40.dp).size(220.dp, 90.dp).shadow(5.dp, shape = RoundedCornerShape(20.dp)),
                colors = ButtonDefaults.buttonColors(colorResource(id = R.color.Yellow)),
                shape = RoundedCornerShape(20.dp)
            ) {
                Text(
                    text = "Receber Eventos",
                    color = colorResource(id = R.color.Dark),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center
                )
            }

            Spacer(modifier = Modifier.size(20.dp))

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(eventos) { evento ->
                    Text(
                        text = "${evento.data} ${evento.hora} - ${evento.nome}",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(vertical = 4.dp)
                            .fillMaxWidth()
                    )
                }
            }
        }
    }

}

class FakeOrientacoesScreenViewModel : OrientacoesScreenViewModel(Application()) {
    init {
        setEventos(
            listOf(
                Evento("Evento Mock 1", "2025-05-23", "10:00", "Descrição 1"),
                Evento("Evento Mock 2", "2025-05-24", "14:00", "Descrição 2")
            )
        )
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GuidenceScreenPreview(){
    GuidenceScreen(
        navController = NavController(LocalContext.current),
        orientacoesScreenViewModel = FakeOrientacoesScreenViewModel()
    )
}

