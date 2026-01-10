package ru.troyanov.yc.entity

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import ru.troyanov.yc.config.UserCred
import java.util.UUID

@Entity
@Table(name = "users")
class User (
    @Id
    val id : UUID = UUID.randomUUID(),
    @Column(name = "user_name", unique = true, nullable = false)
    var username : String,
    var password : String,
    @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL], mappedBy = "user")
    val notes : List<Note>
) {

    fun toUserCred(): UserCred {
        return UserCred(username, password, emptyList())
    }

}