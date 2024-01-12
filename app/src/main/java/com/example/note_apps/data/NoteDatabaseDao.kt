package com.example.note_apps.data

import androidx.compose.runtime.MutableState
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.note_apps.model.NoteObject
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDatabaseDao {
    @Query("SELECT * from notes_table")
    fun getNotes(): Flow<List<NoteObject>>

    @Query("SELECT * from notes_table where id =:id")
    suspend fun getNoteId(id: String): NoteObject

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note: NoteObject)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(note: NoteObject)

    @Query("DELETE from notes_table")
    suspend fun deleteAll()

    @Delete
    suspend fun deleteNote(note: NoteObject)
}
