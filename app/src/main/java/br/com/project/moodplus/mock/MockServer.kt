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
                data = "2025-06-03",
                hora = "09:30",
                nome = "Meditação Guiada",
                descricao = "Sessão rápida de meditação para reduzir o estresse e aumentar o foco no trabalho"
            ),

            Evento(
                data = "2025-06-04",
                hora = "16:00",
                nome = "Oficina de Autocuidado",
                descricao = "Atividade prática para refletir e aplicar técnicas simples de autocuidado no dia a dia"
            ),

            Evento(
                data = "2025-06-06",
                hora = "14:00",
                nome = "Escuta Ativa",
                descricao = "Espaço reservado com profissionais para conversar e ser ouvido sem julgamentos"
            ),

            Evento(
                data = "2025-06-08",
                hora = "17:00",
                nome = "Café com Propósito",
                descricao = "Encontro descontraído para discutir temas importantes sobre propósito e equilíbrio"
            ),

            Evento(
                data = "2025-06-10",
                hora = "11:00",
                nome = "Mind Talks",
                descricao = "Palestras curtas sobre saúde mental"
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