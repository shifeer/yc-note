package ru.troyanov.yc.controller

import org.springframework.ui.Model
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import java.util.UUID

@ControllerAdvice(assignableTypes = [NoteController::class])
class NoteControllerAdvice {

    @ModelAttribute
    fun addUserId(
        @PathVariable userId: UUID,
        model: Model
    ) {
        model.addAttribute("userId", userId)
    }

}