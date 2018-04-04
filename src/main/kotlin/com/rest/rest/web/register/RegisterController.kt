package com.rest.rest.web.register

import com.rest.rest.domain.user.User
import com.rest.rest.domain.user.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.servlet.ModelAndView

@Controller
class RegisterController {
    @Autowired
    lateinit var userService: UserService

    @GetMapping("/register")
    fun register(): ModelAndView {
        return ModelAndView("register/form")
    }

    @PostMapping("/register")
    fun register(form: RegisterForm): ModelAndView {
        if (userService.findUserById(form.id) != null) {
            return ModelAndView("register/form")
                    .addObject("form", form)
                    .addObject("error", "${form.id}가 이미 있습니다.")
        }
        var user = User(form.id, form.name, form.email, BCryptPasswordEncoder().encode(form.password))
        userService.save(user);
        return ModelAndView("redirect:/")
    }

}

data class RegisterForm(
        var id: String,
        var name: String,
        var email: String,
        var password: String
)