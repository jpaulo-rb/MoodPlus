package br.com.project.moodplus.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "MP_MOOD")
data class Mood(

    @PrimaryKey
    // Room não suporte diretamente tipo date
    var data: String = LocalDate.now().toString(),

    var mood: String = "",
    var sentimento: String = "",
    var influencia: String = "",
    var sono: String = "",
    var lideranca: String = "",
    var impacto: String = ""
)

data class Resumo(
    var tipo: String = "",
    var valor: Double = 0.00
)

data class ResultadoResumo(
    val descricao: String,  // a key do map, ex: "Mood"
    val resultado: String,  // o tipo mais escolhido, ex: "Happy"
    val nivel: String       // nível baseado no valor
)

