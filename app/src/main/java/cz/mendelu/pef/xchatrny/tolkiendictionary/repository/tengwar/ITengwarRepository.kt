package cz.mendelu.pef.xchatrny.tolkiendictionary.repository.tengwar

import kotlinx.coroutines.flow.Flow


interface ITengwarRepository {
    suspend fun getTranscription(word: String): Flow<String?>
}