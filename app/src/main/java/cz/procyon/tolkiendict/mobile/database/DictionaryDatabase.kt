package cz.procyon.tolkiendict.mobile.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import cz.procyon.tolkiendict.mobile.database.dao.LanguageDao
import cz.procyon.tolkiendict.mobile.database.dao.SourceDao
import cz.procyon.tolkiendict.mobile.database.dao.WordDao
import cz.procyon.tolkiendict.mobile.model.Language
import cz.procyon.tolkiendict.mobile.model.Source
import cz.procyon.tolkiendict.mobile.model.Word

@Database(
    entities = [Word::class, Language::class, Source::class],
    version = 2,
    exportSchema = true
)
@TypeConverters(Converters::class)
abstract class DictionaryDatabase : RoomDatabase() {

    abstract val wordsDao: WordDao
    abstract val languagesDao: LanguageDao
    abstract val sourcesDao: SourceDao

    companion object {
        @Volatile
        private var INSTANCE: DictionaryDatabase? = null

        fun getDatabase(context: Context): DictionaryDatabase {
            val temp = INSTANCE

            if (temp != null) {
                return temp
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DictionaryDatabase::class.java,
                    "dictionary_database"
                )
                .fallbackToDestructiveMigration()
                .build()

                INSTANCE = instance
                return instance
            }
        }
    }
}