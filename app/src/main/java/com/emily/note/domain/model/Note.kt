package com.emily.note.domain.model

import java.time.LocalDateTime

data class Note(
    val title: String,
    val content: String,
    val timestamp: LocalDateTime,
    val id: Int? = null
)
