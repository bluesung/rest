package com.rest.rest.domain.user

import org.springframework.data.domain.Page

interface UserService {

    fun findUserById(id: String): User?

    fun searchUser(query: String): Page<User>

    fun save(user: User): User

}