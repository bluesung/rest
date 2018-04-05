package com.rest.rest.web.todo

import com.rest.rest.domain.todo.TodoService
import com.rest.rest.domain.user.SearchType
import com.rest.rest.domain.user.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.servlet.ModelAndView
import java.time.LocalDateTime

@Controller
class TodoController {
    @Autowired
    lateinit var todoService: TodoService

    @Autowired
    lateinit var userService: UserService

    @GetMapping("/todo/create")
    fun create(): ModelAndView {
        return ModelAndView("todo/form")
    }

    @PostMapping("/todo/create")
    fun create(form: TodoForm): ModelAndView {
        todoService.create(form.description, form.date, form.userIds)
        return ModelAndView("redirect:/todo/create")
    }

    @GetMapping("/users")
    @ResponseBody
    fun users(@RequestParam("query", required = false, defaultValue = "") query: String,
              @RequestParam("type", required = false, defaultValue = "ID") type: SearchType = SearchType.ID,
              @RequestParam("page", required = false, defaultValue = "0") page: Int,
              @RequestParam("size", required = false, defaultValue = "10") size: Int) =
            userService.searchUser(type, query, PageRequest.of(page, size))


}

data class TodoForm(val description: String, val date: LocalDateTime, val userIds: List<String>)
