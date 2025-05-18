package br.com.project.moodplus.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.project.moodplus.database.repository.MoodRepository
import br.com.project.moodplus.model.Mood
import br.com.project.moodplus.model.Resumo
import java.time.LocalDate

class MoodScreenViewModel(application: Application): AndroidViewModel(application) {

    private val repository = MoodRepository(application.applicationContext)

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

    // A data precisa vir obrigatoraimente no formato yyyy-MM-dd
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

    fun getMood(): Mood {
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
        repository.salvar(getMood())
        } catch (e: Exception) {
            setErro("$e");
        }
    }

    fun excluir() {
        try {
            repository.excluir(getMood())
        } catch (e: Exception) {
            setErro("$e");
        }
    }

    fun buscarPorData() {
        try {
            repository.buscarPorData(_data.value.toString())
        } catch (e: Exception) {
            setErro("$e")
        }
    }

    fun resumos(inicio: LocalDate, fim: LocalDate): Map<String, List<Resumo>> {
        val resumos: MutableMap<String, List<Resumo>> = mutableMapOf()
        try {

            resumos["Mood"] = repository.resumoMood(inicio, fim)
            resumos["Sentimento"] = repository.resumoSentimento(inicio, fim)
            resumos["Influencia"] = repository.resumoInfluencia(inicio, fim)
            resumos["Sono"] = repository.resumoSono(inicio, fim)
            resumos["Lideranca"] = repository.resumoLideranca(inicio, fim)
            resumos["Impacto"] = repository.resumoImpacto(inicio, fim)

            println(resumos)
            return resumos

        } catch (e: Exception) {

            println(e)
            setErro("$e")
            return resumos
        }
    }
}