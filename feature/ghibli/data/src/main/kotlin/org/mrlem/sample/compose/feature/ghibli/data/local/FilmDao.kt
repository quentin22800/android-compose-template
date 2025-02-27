package org.mrlem.sample.compose.feature.ghibli.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface FilmDao {

    ///////////////////////////////////////////////////////////////////////////
    // Query
    ///////////////////////////////////////////////////////////////////////////

    @Query("SELECT * FROM film")
    fun list(): Flow<List<FilmEntity>>

    @Query("SELECT * FROM film WHERE id = :id")
    fun getById(id: String): Flow<FilmEntity>

    ///////////////////////////////////////////////////////////////////////////
    // Insert
    ///////////////////////////////////////////////////////////////////////////

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(film: FilmEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(films: List<FilmEntity>)

    ///////////////////////////////////////////////////////////////////////////
    // Update
    ///////////////////////////////////////////////////////////////////////////

    @Query("UPDATE film SET isFavorite = 1 WHERE id = :id")
    suspend fun favorite(id: String)

    @Query("UPDATE film SET isFavorite = 0 WHERE id = :id")
    suspend fun unfavorite(id: String)

}