package cz.mendelu.pef.xchatrny.tolkiendictionary.api.endpoints

import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query

interface TengwarApi {
    @GET("/")
    suspend fun getTranscription(
        @Query("q") word: String,
        @Query("mode") mode: String = "quenya",
        @Query("font") font: String = "TengwarAnnatar"
    ): ResponseBody
}