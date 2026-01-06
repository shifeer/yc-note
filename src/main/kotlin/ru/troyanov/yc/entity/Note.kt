package ru.troyanov.yc.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.time.Instant
import java.util.UUID

@Entity
@Table(name = "note")
class Note (
    @Id
    val id : UUID = UUID.randomUUID(),
    var title: String,
    @Column(columnDefinition = "TEXT")
    var payload : String? = null,
    val createdAt : Instant = Instant.now(),
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    val user : User
)