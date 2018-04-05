package com.rest.rest.domain.user

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface UserService {

    fun findUserById(id: String): User?

    fun searchUser(type: SearchType, query: String, pageable: Pageable): Page<User>

    fun save(user: User): User

}