package com.rest.rest.domain.user

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class UserServiceImpl : UserService {
    @field:Autowired
    lateinit var userRepository: UserRepository

    override fun findUserById(id: String): User? = userRepository.findById(id).orElse(null)

    override fun save(user: User): User = userRepository.save(user)

    override fun searchUser(type: SearchType, query: String, pageable: Pageable): Page<User> {
        return when (type) {
            SearchType.ID -> userRepository.findByIdLike(query, pageable)
            SearchType.EMAIL -> userRepository.findByEmailLike(query, pageable)
            SearchType.NAME -> userRepository.findByNameLike(query, pageable)
        }
    }
}

enum class SearchType(val description: String) {
    ID("아이디"),
    NAME("이름"),
    EMAIL("이메일")
}