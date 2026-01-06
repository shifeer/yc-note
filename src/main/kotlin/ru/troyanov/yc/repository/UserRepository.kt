package ru.troyanov.yc.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import ru.troyanov.yc.entity.User
import java.util.UUID

@Repository
interface UserRepository : JpaRepository<User, UUID> {

    @Query("select u " +
            "from User u " +
            "where u.username = :username")
    fun findByUsername(username: String): User?

}