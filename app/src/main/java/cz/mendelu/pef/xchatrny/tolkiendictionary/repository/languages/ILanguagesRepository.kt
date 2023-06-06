package cz.mendelu.pef.xchatrny.tolkiendictionary.repository.languages

import cz.mendelu.pef.xchatrny.tolkiendictionary.model.Language
import cz.mendelu.pef.xchatrny.tolkiendictionary.model.relations.LanguageWithWords
import kotlinx.coroutines.flow.Flow
import java.util.UUID

interface ILanguagesRepository {
    suspend fun getAll(): List<Language>

    fun getLanguageWithWords(id: UUID): Flow<List<LanguageWithWords>>

    suspend fun insert(language: Language)

    suspend fun update(language: Language)

    suspend fun delete(language: Language)
}