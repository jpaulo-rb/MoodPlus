package br.com.project.moodplus.service

import android.util.Log
import br.com.project.moodplus.mock.MockServer
import br.com.project.moodplus.model.Evento
import br.com.project.moodplus.viewmodel.OrientacoesScreenViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun buscarEventos (orientacoes: OrientacoesScreenViewModel) {

    CoroutineScope(Dispatchers.IO).launch {
        val retrofit = Retrofit.Builder()
            .baseUrl(MockServer.url())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(EventoService::class.java)

        val call = service.getEventos()

        call.enqueue(object : Callback<List<Evento>> {
            override fun onResponse(call: Call<List<Evento>>, response: Response<List<Evento>>) {
                if (response.isSuccessful) {
                    val eventos = response.body()
                    CoroutineScope(Dispatchers.Main).launch {
                        orientacoes.setEventos(eventos ?: emptyList())
                    }
                    eventos?.forEach {
                        Log.i("MockAPI", "Evento: ${it.nome} em ${it.data} às ${it.hora}, descrição: ${it.descricao}")
                    }
                } else {
                    Log.e("MockAPI", "Erro: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<List<Evento>>, t: Throwable) {
                Log.e("MockAPI", "Erro: ${t.message}")
            }
        })
    }
}