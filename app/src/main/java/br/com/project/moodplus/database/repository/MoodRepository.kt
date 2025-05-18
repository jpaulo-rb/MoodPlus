package br.com.project.moodplus.database.repository

import android.content.Context
import android.database.sqlite.SQLiteConstraintException
import br.com.project.moodplus.database.dao.DbAcess
import br.com.project.moodplus.model.Mood
import br.com.project.moodplus.model.Resumo
import java.time.LocalDate

class MoodRepository(context: Context) {

    private var db = DbAcess.getDb(context).moodDao()

    fun salvar(mood: Mood): Mood {
        return try {
            db.salvar(mood)
            db.buscarPorData(mood.data)
        } catch (e: Exception) {
            throw Exception("Erro ao incluri Mood!\n$e")
        }
    }

    fun atualizar(mood: Mood): Mood {
        return try {
            db.atualizar(mood)
            buscarPorData(mood.data)
        } catch (e: Exception) {
            throw Exception("Erro ao atualizar Mood!\n$e")
        }
    }

    fun excluir(mood: Mood) {
        try {
            db.excluir(mood)
        } catch (e: Exception) {
            throw Exception("Erro ao excluir Mood!\n$e")
        }
    }

    fun buscarPorData(data: String): Mood {
        return try {
            (db.buscarPorData(data))
        } catch (e: Exception) {
            throw Exception("Erro ao buscar Mood!\n$e")
        }
    }

    fun buscarPorPeriodo(inicio: LocalDate, fim: LocalDate): List<Mood> {
        return db.buscarPorPeriodo(inicio.toString(), fim.toString())
    }

    fun resumoMood(inicio: LocalDate, fim: LocalDate): List<Resumo> {
        return db.resumoMood(inicio.toString(), fim.toString())
    }

    fun resumoSentimento(inicio: LocalDate, fim: LocalDate): List<Resumo> {
        return db.resumoSentimento(inicio.toString(), fim.toString())
    }

    fun resumoInfluencia(inicio: LocalDate, fim: LocalDate): List<Resumo> {
        return db.resumoInfluencia(inicio.toString(), fim.toString())
    }

    fun resumoSono(inicio: LocalDate, fim: LocalDate): List<Resumo> {
        return db.resumoSono(inicio.toString(), fim.toString())
    }

    fun resumoLideranca(inicio: LocalDate, fim: LocalDate): List<Resumo> {
        return db.resumoLideranca(inicio.toString(), fim.toString())
    }

    fun resumoImpacto(inicio: LocalDate, fim: LocalDate): List<Resumo> {
        return db.resumoImpacto(inicio.toString(), fim.toString())
    }
}