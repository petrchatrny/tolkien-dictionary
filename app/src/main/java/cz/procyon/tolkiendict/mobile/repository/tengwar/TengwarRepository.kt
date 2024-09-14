package cz.procyon.tolkiendict.mobile.repository.tengwar

import kotlinx.coroutines.flow.Flow


interface TengwarRepository {
    suspend fun getTranscription(word: String): Flow<String?>
}