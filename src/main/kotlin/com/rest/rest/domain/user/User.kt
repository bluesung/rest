package com.rest.rest.domain.user

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.time.LocalDateTime

@Document
class User(
        @Id
        var id: String,
        var name: String,
        var email: String,
        @field:JsonIgnore
        var encPassword: String,
        var auths: List<String> = listOf(),
        @CreatedDate
        var createdAt: LocalDateTime = LocalDateTime.now(),
        @LastModifiedDate
        var modifiedAt: LocalDateTime = LocalDateTime.now()
) : UserDetails {

        // TODO : https://youtrack.jetbrains.com/issue/KT-6653
        override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
                return auths.map { UserGrantedAuthority(it) }.toMutableList()
        }

        override fun isEnabled(): Boolean {
                return true
        }

        override fun getUsername(): String {
                return name
        }

        override fun isCredentialsNonExpired(): Boolean {
                return true
        }

        @JsonIgnore
        override fun getPassword(): String {
                return encPassword
        }

        override fun isAccountNonExpired(): Boolean {
                return true
        }

        override fun isAccountNonLocked(): Boolean {
                return true
        }

}

class UserGrantedAuthority(private val auth: String) : GrantedAuthority {
        override fun getAuthority(): String {
                return auth
        }

}