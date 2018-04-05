package com.rest.rest.domain.todo

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.time.LocalDateTime

interface TodoService {
    fun create(description: String, date: LocalDateTime, userIds: List<String>): Todo?
    fun delete(id: String)
    fun accept(id: String, userId: String, acceptStatus: AcceptStatus)
    fun find(userId: String, pageable: Pageable): Page<Todo>
}