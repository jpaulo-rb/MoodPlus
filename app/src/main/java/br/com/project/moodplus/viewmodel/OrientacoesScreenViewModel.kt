package br.com.project.moodplus.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.project.moodplus.model.Evento

open class OrientacoesScreenViewModel (application: Application): AndroidViewModel(application) {

    internal val _eventos = MutableLiveData<List<Evento>>()
    private val _data = MutableLiveData<String>();
    private val _hora = MutableLiveData<String>();
    private val _nome = MutableLiveData<String>();
    private val _descricao = MutableLiveData<String>();

    val eventos: LiveData<List<Evento>> = _eventos
    val data: LiveData<String> = _data
    val hora: LiveData<String> = _hora
    val nome: LiveData<String> = _nome
    val descricao: LiveData<String> = _descricao

    fun setEventos(value: List<Evento>) {
        _eventos.value = value
    }

    fun setData(value: String) {
        _data.value = value
    }

    fun setHora(value: String) {
        _hora.value = value
    }

    fun setNome(value: String) {
        _nome.value = value
    }

    fun setDescricao(value: String) {
        _descricao.value = value
    }
}