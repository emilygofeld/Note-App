package com.emily.note.domain.repository

import com.emily.note.domain.model.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {
    suspend fun upsertNote(note: Note)

    suspend fun deleteNote(note: Note)

    fun getNotes(): Flow<List<Note>>

    suspend fun getNote(id: Int): Note?
}