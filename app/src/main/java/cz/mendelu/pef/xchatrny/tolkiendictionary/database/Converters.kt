package cz.mendelu.pef.xchatrny.tolkiendictionary.database

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.room.TypeConverter
import java.io.ByteArrayOutputStream
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

    @TypeConverter
    fun fromBitmap(bitmap: Bitmap?): ByteArray? {
        bitmap?.let {
            val outputStream = ByteArrayOutputStream()
            it.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
            return outputStream.toByteArray()
        } ?: run {
            return null
        }
    }

    @TypeConverter
    fun toBitmap(byteArray: ByteArray?): Bitmap? {
        byteArray?.let {
            return BitmapFactory.decodeByteArray(it, 0, byteArray.size)
        } ?: run {
            return null
        }
    }
}
