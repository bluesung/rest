package com.rest.rest

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.mongodb.config.EnableMongoAuditing

@SpringBootApplication
@EnableMongoAuditing
class RestApplication

fun main(args: Array<String>) {
    runApplication<RestApplication>(*args)
}
