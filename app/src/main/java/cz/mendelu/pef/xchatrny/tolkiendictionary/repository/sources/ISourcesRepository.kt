package cz.mendelu.pef.xchatrny.tolkiendictionary.repository.sources

import cz.mendelu.pef.xchatrny.tolkiendictionary.model.Source
import kotlinx.coroutines.flow.Flow

interface ISourcesRepository {
    fun getAll(): Flow<List<Source>>

    suspend fun insert(source: Source)

    suspend fun update(source: Source)

    suspend fun delete(source: Source)
}