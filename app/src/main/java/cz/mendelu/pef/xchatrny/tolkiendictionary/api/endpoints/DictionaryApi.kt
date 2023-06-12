package cz.mendelu.pef.xchatrny.tolkiendictionary.api.endpoints

import cz.mendelu.pef.xchatrny.tolkiendictionary.api.dto.DictionaryDTO
import cz.mendelu.pef.xchatrny.tolkiendictionary.api.dto.DictionarySyncDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface DictionaryApi {

    @GET("dictionaries/")
    suspend fun downloadDictionary(): DictionaryDTO

    @GET("dictionaries/sync")
    suspend fun syncDictionary(@Query("lastSync") lastSyncDate: Long?): DictionarySyncDTO
}