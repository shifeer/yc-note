package ru.troyanov.yc.repository

import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Slice
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import ru.troyanov.yc.entity.Note
import java.util.UUID

@Repository
interface NoteRepository : JpaRepository<Note, UUID> {

    @Query("""
            select n
            from Note n
            where n.user.id = :userId
        """
    )
    fun findByUserID(userId : UUID, pageable: Pageable) : Slice<Note>

}