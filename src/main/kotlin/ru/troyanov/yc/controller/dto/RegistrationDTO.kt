package ru.troyanov.yc.controller.dto

import org.springframework.security.crypto.password.PasswordEncoder
import ru.troyanov.yc.entity.User

class RegistrationDTO(
    val username: String,
    val password: String,
) {

    fun toUser(passwordEncoder: PasswordEncoder): User {
        return User(
            username = username,
            password = passwordEncoder.encode(password) as String,
            notes = emptyList()
        )
    }

}