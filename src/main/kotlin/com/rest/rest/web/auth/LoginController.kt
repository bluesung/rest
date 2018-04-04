package com.rest.rest.web.auth

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.servlet.ModelAndView

@Controller
class LoginController {

    @GetMapping("/login")
    fun login(error: String?, logout: String?): ModelAndView {
        return ModelAndView("login/form")
                .addObject("error", error != null)
                .addObject("login", logout != null)
    }

    @PostMapping("/login")
    fun loginProcess(): String {
        return "j_spring_security_check"
    }

}