package cz.mendelu.pef.xchatrny.tolkiendictionary.util

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

object DateUtils {
    private const val DATE_FORMAT_CS = "dd. MM. yyyy"
    private const val DATE_FORMAT_EN = "yyyy/MM/dd"

    fun getDateString(unixTime: Long): String {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = unixTime

        val format: SimpleDateFormat = if (LanguageUtils.isLanguageCzech()) {
            SimpleDateFormat(DATE_FORMAT_CS, Locale.GERMAN)
        } else {
            SimpleDateFormat(DATE_FORMAT_EN, Locale.ENGLISH)
        }

        return format.format(calendar.time)
    }
}
