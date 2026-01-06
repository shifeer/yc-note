package ru.troyanov.yc.controller

import org.springframework.data.domain.PageRequest
import org.springframework.http.HttpHeaders
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import ru.troyanov.yc.controller.dto.CreateNoteDTO
import ru.troyanov.yc.controller.dto.UpdateNoteDTO
import ru.troyanov.yc.repository.NoteRepository
import ru.troyanov.yc.service.NoteService
import java.util.UUID

@Controller
@RequestMapping("/{userId}/notes")
class NoteController(
    private val noteService: NoteService,
) {

    @GetMapping
    fun all(@PathVariable userId: UUID,
            @RequestParam(defaultValue = "0") page: Int,
            @RequestParam(defaultValue = "10") pageSize: Int,
            model: Model
    ) : String {

        val slice = noteService
            .findByUserID(userId, page, pageSize)
        model.addAttribute("notes", slice.content)
        model.addAttribute("isFirst", slice.isFirst)
        model.addAttribute("isLast", slice.isLast)
        model.addAttribute("page", page)
        model.addAttribute("pageSize", pageSize)
        return "notes"
    }

    @PostMapping
    fun create(@PathVariable userId: UUID,
               dto: CreateNoteDTO
    ) : String {
        noteService.create(dto, userId)
        return "redirect:/${userId}/notes"
    }

    @PutMapping
    fun update(@PathVariable userId: UUID,
               dto: UpdateNoteDTO) : String {
        noteService.update(dto)
        return "redirect:/${userId}/notes"
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: UUID,
               @PathVariable userId: UUID) : String {
        noteService.delete(id)
        return "redirect:/${userId}/notes"
    }

}