package br.com.project.moodplus.mock

import android.util.Log
import br.com.project.moodplus.model.Evento
import com.google.gson.Gson
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer

object MockServer {
    private val server = MockWebServer()

    fun start() {
        try {
            server.start(8080) // Porta fixa
        } catch (e: Exception) {
            Log.e("MockServer", "Erro ao iniciar MockWebServer", e)
        }
    }
    fun url() = server.url("/").toString()

    fun listaEventos() {
        val eventos = listOf(
            Evento(
                data = "2025-06-01",
                hora = "19:00",
                nome = "Bate-Papo",
                descricao = "Evento para bate-papo livre, para conhecimento de seus companheiros"
            ),
            Evento(
                data = "2025-06-05",
                hora = "14:00",
                nome = "Bate-Papo",
                descricao = "Evento para bate-papo livre, para conhecimento de seus companheiros"
            )
        )

        val json = Gson().toJson(eventos)

        server.enqueue(MockResponse()
            .setResponseCode(200)
            .setBody(json)
        )

    }

    fun shutdown() {
        server.shutdown()
    }

}