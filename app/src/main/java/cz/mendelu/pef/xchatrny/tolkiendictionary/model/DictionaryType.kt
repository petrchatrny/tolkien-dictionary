package cz.mendelu.pef.xchatrny.tolkiendictionary.model

import cz.mendelu.pef.xchatrny.tolkiendictionary.repository.words.SearchCriteria

/**
 * TRANSLATION:    TRANSLATION - CZECH
 * CZECH_MEANING:  CZECH - TRANSLATION
 */
data class DictionaryType(
    val language: Language,
    val criterium: SearchCriteria
) {
    fun getDisplayName(czechTitle: String): String {
        return when (criterium) {
            SearchCriteria.CZECH_MEANING -> {
                czechTitle + " - " + language.name
            }

            SearchCriteria.TRANSLATION -> {
                language.name + " - " + czechTitle
            }
        }
    }
}