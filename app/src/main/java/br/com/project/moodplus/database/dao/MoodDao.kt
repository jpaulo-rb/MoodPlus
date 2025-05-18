package br.com.project.moodplus.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import br.com.project.moodplus.model.Mood
import br.com.project.moodplus.model.Resumo

@Dao
interface MoodDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun salvar(mood: Mood)

    @Update
    fun atualizar(mood: Mood)

    @Delete
    fun excluir(mood: Mood)

    @Query("SELECT * FROM MP_MOOD WHERE DATA = :data ORDER BY DATA")
    fun buscarPorData(data: String): Mood

    @Query("SELECT * FROM MP_MOOD WHERE DATA BETWEEN :inicio and :fim ORDER BY DATA")
    fun buscarPorPeriodo(inicio: String, fim: String): List<Mood>

    @Query("select mood tipo, count(mood) valor from mp_mood where data between :inicio and :fim group by 1 order by valor")
    fun resumoMood(inicio: String, fim: String): List<Resumo>

    @Query("select sentimento tipo, count(sentimento) valor from mp_mood where data between :inicio and :fim group by 1 order by valor")
    fun resumoSentimento(inicio: String, fim: String): List<Resumo>

    @Query("select influencia tipo, count(influencia) valor from mp_mood where data between :inicio and :fim group by 1 order by valor")
    fun resumoInfluencia(inicio: String, fim: String): List<Resumo>

    @Query("select sono tipo, count(sono) valor from mp_mood where data between :inicio and :fim group by 1 order by valor")
    fun resumoSono(inicio: String, fim: String): List<Resumo>

    @Query("select lideranca tipo, count(lideranca) valor from mp_mood where data between :inicio and :fim group by 1 order by valor")
    fun resumoLideranca(inicio: String, fim: String): List<Resumo>

    @Query("select impacto tipo, count(impacto) valor from mp_mood where data between :inicio and :fim group by 1 order by valor")
    fun resumoImpacto(inicio: String, fim: String): List<Resumo>

}