package cz.procyon.tolkiendict.mobile.repository.source

import cz.procyon.tolkiendict.mobile.database.dao.SourceDao
import cz.procyon.tolkiendict.mobile.model.Source
import kotlinx.coroutines.flow.Flow
import java.util.UUID

class SourceRoomRepository(private val dao: SourceDao) : SourceRepository {
    override fun getAll(): Flow<List<Source>> {
        return dao.getAll()
    }

    override suspend fun getOne(id: UUID): Source? {
        return dao.getOne(id)
    }

    override suspend fun insert(source: Source) {
        dao.insert(source = source)
    }

    override suspend fun update(source: Source) {
        dao.update(source = source)
    }

    override suspend fun delete(source: Source) {
        dao.delete(source = source)
    }

    override suspend fun insertAll(sources: List<Source>) {
        dao.insertAll(sources = sources)
    }

    override suspend fun deleteDownloaded() {
        dao.deleteDownloaded()
    }

    override suspend fun deleteMultiple(idList: List<UUID>) {
        dao.deleteMultiple(idList = idList)
    }
}