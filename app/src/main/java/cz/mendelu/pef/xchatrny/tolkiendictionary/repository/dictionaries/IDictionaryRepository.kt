package cz.mendelu.pef.xchatrny.tolkiendictionary.repository.dictionaries

import cz.mendelu.pef.xchatrny.tolkiendictionary.api.dto.DictionaryDTO

interface IDictionaryRepository {
    suspend fun downloadDictionary(): DictionaryDTO
}