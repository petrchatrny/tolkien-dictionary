package cz.procyon.tolkiendict.mobile.model

import cz.procyon.tolkiendict.mobile.repository.word.SearchCriteria

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