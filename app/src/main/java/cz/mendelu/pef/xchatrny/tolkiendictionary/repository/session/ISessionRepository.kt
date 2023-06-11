package cz.mendelu.pef.xchatrny.tolkiendictionary.repository.session

interface ISessionRepository {
    suspend fun getLastSyncDateTime(): Long?
    suspend fun updateLastSyncDateTime()
}