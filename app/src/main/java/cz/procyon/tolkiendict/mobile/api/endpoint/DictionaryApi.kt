package cz.procyon.tolkiendict.mobile.api.endpoint

import cz.procyon.tolkiendict.mobile.api.dto.DictionaryDtoEntities
import cz.procyon.tolkiendict.mobile.api.dto.DictionarySyncDto
import retrofit2.http.GET
import retrofit2.http.Query

interface DictionaryApi {

    @GET("dictionary")
    suspend fun downloadDictionary(): DictionaryDtoEntities

    @GET("dictionary/sync")
    suspend fun syncDictionary(@Query("lastSync") lastSyncDate: Long?): DictionarySyncDto
}