package com.emily.note.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.emily.note.data.entity.NoteEntity

@Database(
    entities = [NoteEntity::class],
    version = 1,
    exportSchema = false
)
abstract class NoteDatabase: RoomDatabase() {
    abstract val dao: NoteDao
}