package br.com.dictionmaster.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.dictionmaster.database.entities.WordDetailEntity
import retrofit2.http.PUT

@Dao
interface WordsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(wordDetailEntity: WordDetailEntity)

    @Query("SELECT * FROM Words WHERE word == :word")
    suspend fun findWord(word: String): List<WordDetailEntity>

}