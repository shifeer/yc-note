package ru.troyanov.yc.controller.dto

import java.util.UUID

class UpdateNoteDTO (
    val id: UUID,
    val title: String,
    val payload: String?,
)