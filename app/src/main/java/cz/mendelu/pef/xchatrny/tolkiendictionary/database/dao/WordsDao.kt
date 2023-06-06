package cz.mendelu.pef.xchatrny.tolkiendictionary.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import cz.mendelu.pef.xchatrny.tolkiendictionary.model.Word
import cz.mendelu.pef.xchatrny.tolkiendictionary.model.relations.WordWithSource
import kotlinx.coroutines.flow.Flow
import java.util.UUID

@Dao
interface WordsDao {
    @Query("SELECT * FROM words")
    fun getAll(): Flow<List<Word>>

    @Query("SELECT * FROM words WHERE czech_meaning LIKE '%' || :query || '%' AND id_language = :idLanguage")
    fun getAllByCzechMeaning(query: String, idLanguage: UUID): Flow<List<Word>>

    @Query("SELECT * FROM words WHERE translation LIKE  '%' || :query || '%' AND id_language = :idLanguage")
    fun getAllByTranslation(query: String, idLanguage: UUID): Flow<List<Word>>

    @Transaction
    @Query("SELECT * FROM words WHERE id_word=:id")
    suspend fun getWordWithSourceById(id: UUID): WordWithSource

    @Query("SELECT * FROM words WHERE is_bookmarked = 1")
    fun getBookmarkedWords(): Flow<List<Word>>

    @Insert
    suspend fun insert(word: Word)

    @Update
    suspend fun update(word: Word)

    @Delete
    suspend fun delete(word: Word)
}