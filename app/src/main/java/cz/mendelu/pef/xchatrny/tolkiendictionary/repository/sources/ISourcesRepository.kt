package cz.mendelu.pef.xchatrny.tolkiendictionary.repository.sources

import cz.mendelu.pef.xchatrny.tolkiendictionary.model.Source
import kotlinx.coroutines.flow.Flow
import java.util.UUID

interface ISourcesRepository {
    fun getAll(): Flow<List<Source>>

    suspend fun getOne(id: UUID): Source?

    suspend fun insert(source: Source)

    suspend fun update(source: Source)

    suspend fun delete(source: Source)

    suspend fun insertAll(sources: List<Source>)

    suspend fun deleteDownloaded()

    suspend fun deleteMultiple(idList: List<UUID>)
}