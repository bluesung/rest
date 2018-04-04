package com.rest.rest.web

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.servlet.ModelAndView

@Controller
class HelloController {

    @GetMapping("/")
    fun hello(): ModelAndView {
        return ModelAndView("hello/index")
                .addObject("hello", "hello! world")
    }
}