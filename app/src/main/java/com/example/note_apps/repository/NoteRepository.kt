package com.example.note_apps.repository

import android.view.KeyEvent.DispatcherState
import com.example.note_apps.data.NoteDatabaseDao
import com.example.note_apps.model.NoteObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class NoteRepository @Inject constructor(private val noteDatabaseDao: NoteDatabaseDao) {
    suspend fun addNote(note: NoteObject)    = noteDatabaseDao.insert(note)
    suspend fun updateNote(note: NoteObject) = noteDatabaseDao.update(note)
    suspend fun deleteNote(note: NoteObject) = noteDatabaseDao.deleteNote(note)
    suspend fun deleteAllNote()              = noteDatabaseDao.deleteAll()
    fun getAllNote(): Flow<List<NoteObject>> = noteDatabaseDao.getNotes().flowOn(Dispatchers.IO).conflate()
}