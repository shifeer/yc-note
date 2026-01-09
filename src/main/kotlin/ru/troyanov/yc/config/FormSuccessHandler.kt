package ru.troyanov.yc.config

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import org.springframework.stereotype.Component
import ru.troyanov.yc.repository.UserRepository

@Component
class FormSuccessHandler(
    private val userRepository: UserRepository,
): AuthenticationSuccessHandler {
    override fun onAuthenticationSuccess(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authentication: Authentication
    ) {
        userRepository.findByUsername(authentication.name)
            ?.let { response.sendRedirect("/${it.id}/notes") }
            ?: throw IllegalStateException("User not found")
    }
}