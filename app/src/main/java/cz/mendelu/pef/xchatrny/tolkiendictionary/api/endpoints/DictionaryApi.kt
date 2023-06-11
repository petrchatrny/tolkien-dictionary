package cz.mendelu.pef.xchatrny.tolkiendictionary.api.endpoints

import cz.mendelu.pef.xchatrny.tolkiendictionary.api.dto.DictionaryDTO
import retrofit2.http.GET

interface DictionaryApi {

    @GET("dictionaries/")
    suspend fun downloadDictionary(): DictionaryDTO
}