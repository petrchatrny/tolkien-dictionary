package cz.procyon.tolkiendict.mobile.repository.source

import cz.procyon.tolkiendict.mobile.model.Source
import kotlinx.coroutines.flow.Flow
import java.util.UUID

interface SourceRepository {
    fun getAll(): Flow<List<Source>>

    suspend fun getOne(id: UUID): Source?

    suspend fun insert(source: Source)

    suspend fun update(source: Source)

    suspend fun delete(source: Source)

    suspend fun insertAll(sources: List<Source>)

    suspend fun deleteDownloaded()

    suspend fun deleteMultiple(idList: List<UUID>)
}