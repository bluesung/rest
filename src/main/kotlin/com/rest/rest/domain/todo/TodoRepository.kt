package com.rest.rest.domain.todo

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query

interface TodoRepository : MongoRepository<Todo, String> {

    @Query("{ 'userAccept' : { 'userId' : ?0 } }")
    fun findAllByUserId(userId: String, pageable: Pageable): Page<Todo>

}