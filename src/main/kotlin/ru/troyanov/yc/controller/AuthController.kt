package ru.troyanov.yc.controller

import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import ru.troyanov.yc.controller.dto.RegistrationDTO
import ru.troyanov.yc.repository.UserRepository

@Controller
class AuthController(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
) {

    @GetMapping("/login")
    fun loginForm(): String = "login"

    @GetMapping("/registration")
    fun registrationForm(): String = "registration"

    @PostMapping("/registration")
    fun registration(
        dto: RegistrationDTO
    ): String {
        userRepository.save(dto.toUser(passwordEncoder))

        return "redirect:/login"
    }

}