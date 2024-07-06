package com.emily.note.data.repository

import com.emily.note.data.local.NoteDao
import com.emily.note.data.mapper.toEntity
import com.emily.note.data.mapper.toNote
import com.emily.note.domain.model.Note
import com.emily.note.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class NoteRepositoryImpl(
    private val dao: NoteDao
): NoteRepository {
    override suspend fun upsertNote(note: Note) {
        dao.upsertNote(note.toEntity())
    }

    override suspend fun deleteNote(note: Note) {
        dao.deleteNote(note.toEntity())
    }

    override fun getNotes(): Flow<List<Note>> {
        return dao.getNotes().map { noteEntities ->
            noteEntities.map { noteEntity ->
                noteEntity.toNote()
            }
        }
    }

    override suspend fun getNote(id: Int): Note? {
        return dao.getNote(id)?.toNote()
    }
}