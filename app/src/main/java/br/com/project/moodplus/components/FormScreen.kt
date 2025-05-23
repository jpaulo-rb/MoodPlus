package br.com.project.moodplus.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import br.com.project.moodplus.R
import br.com.project.moodplus.model.Pergunta
import br.com.project.moodplus.viewmodel.MoodScreenViewModel
import java.time.LocalDate

@Composable
fun FormScreen(
    navController: NavController,
    moodScreenViewModel: MoodScreenViewModel
) {

    val perguntas = listOf(
        Pergunta("Como você está se sentindo hoje?", listOf("Motivado", "Cansado", "Preocupado", "Estressado", "Satisfeito", "Animado")),
        Pergunta("O que influenciou seu humor?", listOf("Trabalho", "Família", "Saúde", "Relacionamentos", "Estudos")),
        Pergunta("Como foi seu sono?", listOf("Muito bom", "Regular", "Ruim")),
        Pergunta("Como você avalia a relação com a liderança na empresa?", listOf("Muito boa", "Boa", "Mais ou menos", "Ruim", "Muito ruim")),
        Pergunta("Como você avalia o impacto do trabalho na sua vida pessoal?", listOf("Muito boa", "Boa", "Mais ou menos", "Ruim", "Muito ruim"))
    )

    var perguntaAtual by remember { mutableIntStateOf(0) }
    val respostasSelecionadas = remember { mutableStateMapOf<Int, List<String>>() }
    val opcoesSelecionadas = remember { mutableStateMapOf<String, Boolean>() }

    val pergunta = perguntas[perguntaAtual]

    val erro by moodScreenViewModel.erro.observeAsState(initial = "")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.lightBlue))
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo"

        )

        Text(
            text = pergunta.texto,
            color = colorResource(id = R.color.Dark),
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        // Checkboxes dinâmicas
        pergunta.opcoes.forEach { opcao ->
            val checked = opcoesSelecionadas[opcao] ?: false
            Row(modifier = Modifier.fillMaxWidth().padding(start = 22.dp), verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = checked,
                    onCheckedChange = {
                        opcoesSelecionadas[opcao] = it
                    }
                )
                Text(opcao, color = colorResource(id = R.color.Dark), fontWeight = FontWeight.SemiBold)
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                // Salvar respostas marcadas para a pergunta atual
                val respostas = opcoesSelecionadas.filter { it.value }.map { it.key }
                respostasSelecionadas[perguntaAtual] = respostas

                // Limpar seleção atual
                opcoesSelecionadas.clear()

                // Avançar
                if (perguntaAtual < perguntas.size - 1) {
                    perguntaAtual++
                } else {
                    // Finalizar
                    moodScreenViewModel.setData(LocalDate.now().toString())
                    moodScreenViewModel.setSentimento(respostasSelecionadas[0]?.firstOrNull() ?: "Vazio")
                    moodScreenViewModel.setInfluencia(respostasSelecionadas[1]?.firstOrNull() ?: "Vazio")
                    moodScreenViewModel.setSono(respostasSelecionadas[2]?.firstOrNull() ?: "Vazio")
                    moodScreenViewModel.setLideranca(respostasSelecionadas[3]?.firstOrNull() ?: "Vazio")
                    moodScreenViewModel.setImpacto(respostasSelecionadas[4]?.firstOrNull() ?: "Vazio")

                    moodScreenViewModel.salvar()

                    moodScreenViewModel.pResumos(LocalDate.now(), LocalDate.now())
                    navController.navigate("HomeScreen")
                }
            },
            colors = ButtonDefaults.buttonColors(colorResource(id = R.color.Yellow)),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .size(220.dp, 64.dp)
                .padding(horizontal = 32.dp)
                .shadow(5.dp, shape = RoundedCornerShape(20.dp))
        ) {
            Text(
                "Submit",
                color = colorResource(id = R.color.Dark),
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun FormScreenPreview(){
    val moodScreenViewModel: MoodScreenViewModel = viewModel()
    FormScreen(navController = NavController(LocalContext.current), moodScreenViewModel)
}