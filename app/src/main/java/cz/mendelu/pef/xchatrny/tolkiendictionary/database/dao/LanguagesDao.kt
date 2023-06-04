package cz.mendelu.pef.xchatrny.tolkiendictionary.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import cz.mendelu.pef.xchatrny.tolkiendictionary.model.Language
import cz.mendelu.pef.xchatrny.tolkiendictionary.model.relations.LanguageWithWords
import kotlinx.coroutines.flow.Flow
import java.util.UUID

@Dao
interface LanguagesDao {
    @Query("SELECT * FROM languages")
    fun getAll(): Flow<List<Language>>

    @Transaction
    @Query("SELECT * FROM languages WHERE id_language=:id")
    fun getLanguageWithWords(id: UUID): List<LanguageWithWords>

    @Insert
    suspend fun insert(language: Language)

    @Update
    suspend fun update(language: Language)

    @Delete
    suspend fun delete(language: Language)
}