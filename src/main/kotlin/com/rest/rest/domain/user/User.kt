package com.rest.rest.domain.user

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
class User(
        @Id
        var id: String,
        var name: String,
        var email: String,
        var password: String
) {
}