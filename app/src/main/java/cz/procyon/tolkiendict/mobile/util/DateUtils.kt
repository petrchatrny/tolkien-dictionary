package cz.procyon.tolkiendict.mobile.util

import java.text.SimpleDateFormat
import java.util.*

object DateUtils {
    private const val DATE_FORMAT_CS = "dd. MM. yyyy"
    private const val DATE_FORMAT_EN = "yyyy/MM/dd"

    fun getDateString(unixTime: Long): String {
        val calendar = Calendar.getInstance()
        calendar.time = Date(unixTime * 1000)

        val format: SimpleDateFormat = if (LanguageUtils.isLanguageCzech()) {
            SimpleDateFormat(DATE_FORMAT_CS, Locale.GERMAN)
        } else {
            SimpleDateFormat(DATE_FORMAT_EN, Locale.ENGLISH)
        }

        return format.format(calendar.time)
    }

    fun getCurrentUnixTime(): Long = Calendar.getInstance().timeInMillis / 1000
}
