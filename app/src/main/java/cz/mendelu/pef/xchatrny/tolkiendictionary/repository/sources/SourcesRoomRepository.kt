package cz.mendelu.pef.xchatrny.tolkiendictionary.repository.sources

import cz.mendelu.pef.xchatrny.tolkiendictionary.database.dao.SourcesDao
import cz.mendelu.pef.xchatrny.tolkiendictionary.model.Source
import kotlinx.coroutines.flow.Flow
import java.util.UUID

class SourcesRoomRepository(private val dao: SourcesDao) : ISourcesRepository {
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