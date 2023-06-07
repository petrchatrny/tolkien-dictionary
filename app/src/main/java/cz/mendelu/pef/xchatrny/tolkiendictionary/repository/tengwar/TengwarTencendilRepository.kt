package cz.mendelu.pef.xchatrny.tolkiendictionary.repository.tengwar

import cz.mendelu.pef.xchatrny.tolkiendictionary.api.endpoints.TengwarApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.jsoup.Jsoup

class TengwarTencendilRepository(private val api: TengwarApi) : ITengwarRepository {
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