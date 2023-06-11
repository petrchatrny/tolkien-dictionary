package cz.mendelu.pef.xchatrny.tolkiendictionary.repository.sources

import cz.mendelu.pef.xchatrny.tolkiendictionary.database.dao.SourcesDao
import cz.mendelu.pef.xchatrny.tolkiendictionary.model.Source
import kotlinx.coroutines.flow.Flow

class SourcesRoomRepository(private val dao: SourcesDao) : ISourcesRepository {
    override fun getAll(): Flow<List<Source>> {
        return dao.getAll()
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
        dao.insertAll(sources)
    }

    override suspend fun deleteDownloaded() {
        dao.deleteAll()
    }
}