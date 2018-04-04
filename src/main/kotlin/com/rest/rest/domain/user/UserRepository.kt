package com.rest.rest.domain.user

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.mongodb.repository.MongoRepository

interface UserRepository : MongoRepository<User, String> {
    fun findByNameLike(name: String, page: Pageable): Page<User>
    fun findByIdLike(id: String, page: Pageable): Page<User>
    fun findByEmailLike(email: String, page: Pageable): Page<User>
}