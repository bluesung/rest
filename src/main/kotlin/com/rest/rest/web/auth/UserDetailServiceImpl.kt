package com.rest.rest.web.auth

import com.rest.rest.domain.user.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserDetailServiceImpl : UserDetailsService {
    @Autowired
    lateinit var userService: UserService

    override fun loadUserByUsername(username: String): UserDetails? = userService.findUserById(username)

}