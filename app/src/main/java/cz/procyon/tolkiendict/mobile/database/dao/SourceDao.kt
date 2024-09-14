package cz.procyon.tolkiendict.mobile.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import cz.procyon.tolkiendict.mobile.model.Source
import kotlinx.coroutines.flow.Flow
import java.util.UUID

@Dao
interface SourceDao {
    @Query("SELECT * FROM sources")
    fun getAll(): Flow<List<Source>>

    @Query("SELECT * FROM sources WHERE id_source=:id")
    suspend fun getOne(id: UUID): Source?

    @Insert
    suspend fun insert(source: Source)

    @Update
    suspend fun update(source: Source)

    @Delete
    suspend fun delete(source: Source)

    @Insert
    suspend fun insertAll(sources: List<Source>)

    @Query("DELETE FROM sources WHERE added_by_admin = 1")
    suspend fun deleteDownloaded()

    @Query("DELETE FROM sources WHERE id_source in (:idList)")
    suspend fun deleteMultiple(idList: List<UUID>)
}