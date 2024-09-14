package cz.procyon.tolkiendict.mobile.repository.tengwar

import cz.procyon.tolkiendict.mobile.api.endpoint.TengwarApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.jsoup.Jsoup

class TengwarRestRepository(private val api: TengwarApi) : TengwarRepository {
    override suspend fun getTranscription(word: String): Flow<String?> = flow {
        emit(parseTengwar(api.getTranscription(word).string()))
    }

    private fun parseTengwar(html: String): String? {
        val elements = Jsoup.parse(html).getElementsByClass("TengwarAnnatar")
        return if (elements.size > 0) {
            elements[0].text()
        } else {
            null
        }
    }
}