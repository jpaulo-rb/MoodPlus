package br.com.project.moodplus.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.project.moodplus.database.repository.MoodRepository
import br.com.project.moodplus.model.Mood
import br.com.project.moodplus.model.ResultadoResumo
import br.com.project.moodplus.model.Resumo
import java.time.LocalDate

class MoodScreenViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = MoodRepository(application.applicationContext)

    private val _moodModel = MutableLiveData<Mood>()
    val moodModel: LiveData<Mood> = _moodModel
    fun setMoodModel(value: Mood) {
        _moodModel.value = value
        setData(value.data)
        setMood(value.mood)
        setSentimento(value.sentimento)
        setInfluencia(value.influencia)
        setSono(value.sono)
        setLideranca(value.lideranca)
        setImpacto(value.impacto)
    }

    // Cada val representa a escolha do usuário. Mood = emoji e assim por diante.
    // A data é a primary key, se não for informada nenhum irá ser passada a data atual.
    private val _data = MutableLiveData<String>();
    private val _mood = MutableLiveData<String>();
    private val _sentimento = MutableLiveData<String>();
    private val _influencia = MutableLiveData<String>();
    private val _sono = MutableLiveData<String>();
    private val _lideranca = MutableLiveData<String>();
    private val _impacto = MutableLiveData<String>();
    private val _erro = MutableLiveData<String>();

    val data: LiveData<String> = _data
    val mood: LiveData<String> = _mood
    val sentimento: LiveData<String> = _sentimento
    val influencia: LiveData<String> = _influencia
    val sono: LiveData<String> = _sono
    val lideranca: LiveData<String> = _lideranca
    val impacto: LiveData<String> = _impacto
    val erro: LiveData<String> = _erro

    fun setData(value: String) {
        _data.value = value
    }

    fun setMood(value: String) {
        _mood.value = value
    }

    fun setSentimento(value: String) {
        _sentimento.value = value
    }

    fun setInfluencia(value: String) {
        _influencia.value = value
    }

    fun setSono(value: String) {
        _sono.value = value
    }

    fun setLideranca(value: String) {
        _lideranca.value = value
    }

    fun setImpacto(value: String) {
        _impacto.value = value
    }

    fun setErro(value: String) {
        _erro.value = value
    }

    private fun getMood(): Mood {
        val mood = Mood()
        mood.data = this.data.value.toString()
        mood.mood = this.mood.value.toString()
        mood.sentimento = this.sentimento.value.toString()
        mood.influencia = this.influencia.value.toString()
        mood.sono = this.sono.value.toString()
        mood.lideranca = this.lideranca.value.toString()
        mood.impacto = this.impacto.value.toString()
        return mood
    }

    fun salvar() {
        try {
            setMoodModel(repository.salvar(getMood()))
        } catch (e: Exception) {
            setErro("$e");
        }
    }

    //Primeira ideia, retornar quantas vezes cada foi escolhido
    private fun resumos(inicio: LocalDate, fim: LocalDate): Map<String, List<Resumo>> {
        val resumos: MutableMap<String, List<Resumo>> = mutableMapOf()
        try {
            resumos["Mood"] = repository.resumoMood(inicio, fim).filter{it.tipo != "Vazio"}
            resumos["Sentimento"] = repository.resumoSentimento(inicio, fim).filter{it.tipo != "Vazio"}
            resumos["Influencia"] = repository.resumoInfluencia(inicio, fim).filter{it.tipo != "Vazio"}
            resumos["Sono"] = repository.resumoSono(inicio, fim).filter{it.tipo != "Vazio"}
            resumos["Lideranca"] = repository.resumoLideranca(inicio, fim).filter{it.tipo != "Vazio"}
            resumos["Impacto"] = repository.resumoImpacto(inicio, fim).filter{it.tipo != "Vazio"}
            println(resumos)
            return resumos
        } catch (e: Exception) {
            println(e)
            setErro("$e")
            return resumos
        }
    }

    //Segunda ideia, retornar a porcentagem do que foi escolhido
    private fun pResumos(inicio: LocalDate, fim: LocalDate): Map<String, List<Resumo>> {
        val pResumos: MutableMap<String, List<Resumo>> = mutableMapOf()
        try {
            val resumos = resumos(inicio, fim)
            for ((tipo, resumo) in resumos) {
                val total = resumo.sumOf { it.valor }
                if (total > 0) {
                    val listaPorcentagem = resumo.map { resumo ->
                        val percentual = (resumo.valor.toDouble() / total) * 100
                        Resumo(
                            tipo = resumo.tipo, valor = percentual
                        )
                    }
                    pResumos[tipo] = listaPorcentagem
                }
            }
            println(pResumos)
            return pResumos
        } catch (e: Exception) {
            println(e)
            setErro("$e")
            return pResumos;
        }
    }

    fun buscarPorData(data: LocalDate): Mood? {
        return try {
            repository.buscarPorData(data.toString())
        } catch (e: Exception) {
            Log.e("MoodScreenViewModel", "Erro: ${e.message}")
            return null
        }
    }

    fun buscarPorPeriodo(inicio: LocalDate, fim: LocalDate): List<Mood>? {
        return try {
            repository.buscarPorPeriodo(inicio, fim)
        } catch (e: Exception) {
            Log.e("MoodScreenViewModel", "Erro: ${e.message}")
            return null
        }
    }

    private fun definirNivel(percentual: Double): String = when (percentual) {
        in 0.0..25.0 -> "Neutro"
        in 26.0..50.0 -> "Leve"
        in 51.0..75.0 -> "Moderado"
        in 76.0..100.0 -> "Agudo"
        else -> "Indefinido"
    }

    // Terceira ideia, retornar somente o que foi mais escolhido, e o nível dele de acordo com o PDF
    fun analisarResumo(inicio: LocalDate, fim: LocalDate): List<ResultadoResumo> {
        val dados = pResumos(inicio, fim)
        val resultado = dados.map { (descricao, listaResumo) ->
            val maiorResumo = listaResumo.maxByOrNull { it.valor }
            if (maiorResumo != null) {
                val nivel = definirNivel(maiorResumo.valor)
                ResultadoResumo(descricao, maiorResumo.tipo, nivel)
            } else {
                ResultadoResumo(descricao, "Nenhum", "Indefinido")
            }
        }
        println(resultado)
        return resultado
    }
}