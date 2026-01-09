package ru.troyanov.yc.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.ProviderManager
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.AuthenticationSuccessHandler

@Configuration
class SecurityConfig {

    @Bean
    fun securityFilterChain(http: HttpSecurity,
                            manager: AuthenticationManager,
                            successHandler: AuthenticationSuccessHandler
    ): SecurityFilterChain {
        return http
            .authorizeHttpRequests {
                it
                    .requestMatchers("/login", "/registration", "/css/**", "/js/**").permitAll()
                    .anyRequest().authenticated()
            }
            .formLogin {
                it.loginPage("/login")
                it.successHandler(successHandler)
                it.permitAll()
            }
            .logout {
                it.logoutUrl("/logout")
                it.logoutSuccessUrl("/login")
                it.invalidateHttpSession(true)
                it.deleteCookies("JSESSIONID")
            }

            .authenticationManager(manager)
            .build()
    }

    @Bean
    @Primary
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun authenticationProvider(
        userDetailsService: UserDetailsService,
        passwordEncoder: PasswordEncoder
    ): AuthenticationProvider = DaoAuthenticationProvider(userDetailsService).apply {
        setPasswordEncoder(passwordEncoder)
    }

    @Bean
    fun authenticationManager(
        provider: AuthenticationProvider
    ): AuthenticationManager = ProviderManager(provider)

}