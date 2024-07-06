package com.emily.note.data.mapper

import com.emily.note.data.entity.NoteEntity
import com.emily.note.domain.model.Note
import java.time.LocalDateTime
import java.time.ZoneOffset

fun Note.toEntity(): NoteEntity = NoteEntity(
    title = title,
    content = content,
    timestamp = timestamp.toEpochSecond(ZoneOffset.UTC),
    id = id
)

fun NoteEntity.toNote(): Note = Note(
    title = title,
    content = content,
    timestamp = LocalDateTime.ofEpochSecond(timestamp, 0, ZoneOffset.UTC),
    id = id
)