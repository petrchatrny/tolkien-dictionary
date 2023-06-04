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
    fun fromBitmap(bitmap: Bitmap): ByteArray {
        val outputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
        return outputStream.toByteArray()
    }

    @TypeConverter
    fun toBitmap(byteArray: ByteArray): Bitmap {
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
    }
}
