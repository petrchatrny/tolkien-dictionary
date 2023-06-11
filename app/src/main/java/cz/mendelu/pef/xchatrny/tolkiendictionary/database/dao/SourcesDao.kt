package cz.mendelu.pef.xchatrny.tolkiendictionary.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import cz.mendelu.pef.xchatrny.tolkiendictionary.model.Source
import kotlinx.coroutines.flow.Flow

@Dao
interface SourcesDao {
    @Query("SELECT * FROM sources")
    fun getAll(): Flow<List<Source>>

    @Insert
    suspend fun insert(source: Source)

    @Update
    suspend fun update(source: Source)

    @Delete
    suspend fun delete(source: Source)

    @Insert
    suspend fun insertAll(sources: List<Source>)

    @Query("DELETE FROM sources WHERE added_by_admin = 1")
    suspend fun deleteAll()
}