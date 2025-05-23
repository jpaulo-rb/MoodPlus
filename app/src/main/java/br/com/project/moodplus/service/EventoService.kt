package br.com.project.moodplus.service

import br.com.project.moodplus.model.Evento
import retrofit2.Call
import retrofit2.http.GET

interface EventoService {
    @GET("/eventos")
    fun getEventos(): Call<List<Evento>>
}