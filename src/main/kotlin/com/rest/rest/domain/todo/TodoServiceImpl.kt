package com.rest.rest.domain.todo

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class TodoServiceImpl : TodoService {
    @Autowired
    lateinit var todoRepository: TodoRepository

    override fun delete(id: String) {
        val context = SecurityContextHolder.getContext()
        val details = context.authentication.details
        if (details is UserDetails && details.username != null) {
            todoRepository.findById(id)
                    .ifPresent({ o ->
                        run {
                            o.isDeleted = true
                            o.modifiedBy = details.username
                            o.lastModifiedDate = LocalDateTime.now()
                            todoRepository.save(o)
                        }
                    })
        }
    }

    override fun accept(id: String, userId: String, acceptStatus: AcceptStatus) {
        todoRepository.findById(id)
                .ifPresent({ o ->
                    run {
                        o.userAccept.stream().filter({ it.userId == userId })
                                .forEach({ it.accept = acceptStatus })
                        todoRepository.save(o)
                    }
                })

    }

    override fun find(userId: String, pageable: Pageable): Page<Todo> =
            todoRepository.findAllByUserId(userId, pageable)

    override fun create(description: String, date: LocalDateTime, userIds: List<String>): Todo? {
        val context = SecurityContextHolder.getContext()
        val details = context.authentication.details
        if (details is UserDetails && details.username != null) {
            var todo = Todo(date, description, userIds.map { UserAccept(it) }.toMutableList(), details.username, details.username)
            if (todo.userAccept.find { it.userId == details.username } == null) {
                todo.userAccept.add(UserAccept(details.username))
            }
            return todoRepository.save(todo)
        }
        return null;
    }
}