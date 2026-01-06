package ru.troyanov.yc.service

import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Slice
import org.springframework.data.domain.Sort
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.troyanov.yc.controller.dto.CreateNoteDTO
import ru.troyanov.yc.controller.dto.UpdateNoteDTO
import ru.troyanov.yc.entity.Note
import ru.troyanov.yc.entity.User
import ru.troyanov.yc.repository.NoteRepository
import ru.troyanov.yc.repository.UserRepository
import java.util.UUID

@Service
class NoteService(
    private val noteRepository: NoteRepository,
    private val userRepository: UserRepository
) {

    @Transactional(readOnly = true)
    fun findByUserID(userId: UUID, page: Int, pageSize: Int): Slice<Note> =
        noteRepository.findByUserID(userId,
            PageRequest.of(page, pageSize,
                Sort.by(Sort.Direction.DESC, "createdAt")))

    @Transactional
    fun create(dto: CreateNoteDTO, userId: UUID) {
        val user = userRepository.findByIdOrNull(userId)
        user?.let {
            val note = createNote(it, dto.title, dto.payload)
            noteRepository.save(note)
        }
    }

    @Transactional
    fun update(dto: UpdateNoteDTO) {
        val note = noteRepository.findByIdOrNull(dto.id)
        note?.let {
            it.title = dto.title
            it.payload = dto.payload
            noteRepository.save(it)
        }
    }

    @Transactional
    fun delete(id: UUID) {
        noteRepository.deleteById(id)
    }

    private fun createNote(user: User, title: String, payload: String?): Note {
        return Note(
            title = title,
            user = user,
            payload = payload
        )
    }

}