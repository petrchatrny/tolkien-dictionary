package cz.procyon.tolkiendict.mobile.database

import androidx.room.TypeConverter
import java.util.UUID

class Converters {
    @TypeConverter
    fun toUUID(value: String?): UUID? {
        value?.let {
            return UUID.fromString(it)
        } ?: run {
            return null
        }
    }

    @TypeConverter
    fun fromUUID(value: UUID?): String? {
        value?.let {
            return value.toString()
        } ?: run {
            return null
        }
    }
}
