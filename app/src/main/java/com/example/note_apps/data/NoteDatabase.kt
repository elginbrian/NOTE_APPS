package com.example.note_apps.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.note_apps.model.NoteObject
import com.example.note_apps.util.DateConverter
import com.example.note_apps.util.UUIDConverter

@Database(entities = [NoteObject::class], version = 1, exportSchema = false)
@TypeConverters(DateConverter::class, UUIDConverter::class)
abstract class NoteDatabase: RoomDatabase() {
    abstract fun noteDao(): NoteDatabaseDao
}