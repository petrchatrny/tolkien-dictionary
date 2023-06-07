package cz.mendelu.pef.xchatrny.tolkiendictionary.util

import java.util.Locale

object LanguageUtils {
    private const val CZECH = "cs"
    private const val SLOVAK = "sk"

    fun isLanguageCzech(): Boolean {
        val language = Locale.getDefault().language
        return language.equals(CZECH) || language.equals(SLOVAK)
    }

}
