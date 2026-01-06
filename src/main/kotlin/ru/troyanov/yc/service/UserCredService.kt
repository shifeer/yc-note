package ru.troyanov.yc.service

import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import ru.troyanov.yc.repository.UserRepository

@Service
class UserCredService(
    private val userRepository: UserRepository
): UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        return userRepository.findByUsername(username)
            ?.toUserCred()
            ?: throw UsernameNotFoundException("User not found")
    }
}