package com.emily.note.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.emily.note.core.Constants

@Entity(tableName = Constants.TABLE_NAME)
data class NoteEntity(
    val title: String,
    val content: String,
    val timestamp: Long,

    @PrimaryKey(autoGenerate = true)
    val id: Int
)
