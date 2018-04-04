package com.rest.rest.domain.user

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service

@Service
class UserServiceImpl : UserService {
    @field:Autowired
    lateinit var userRepository: UserRepository

    override fun findUserById(id: String): User? = userRepository.findById(id).orElse(null)

    override fun save(user: User): User = userRepository.save(user)

    override fun searchUser(query: String): Page<User> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}