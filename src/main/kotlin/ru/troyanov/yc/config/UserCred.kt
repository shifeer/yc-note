package ru.troyanov.yc.config

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class UserCred(
    private val username: String,
    private val password: String,
    private val authorities: List<String>
): UserDetails {

    override fun getAuthorities(): Collection<out GrantedAuthority> {
        return authorities.map { SimpleGrantedAuthority(it) }
    }

    override fun getPassword(): String? {
        return password
    }

    override fun getUsername(): String {
        return username
    }
}