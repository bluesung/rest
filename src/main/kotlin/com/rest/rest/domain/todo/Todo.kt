package com.rest.rest.domain.todo

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document
class Todo(
        var date: LocalDateTime,
        var description: String,
        var userAccept: MutableList<UserAccept> = mutableListOf(),
        var createdBy: String,
        var modifiedBy: String
) {
    @Id
    var id: String? = null
    var todoStatus: TodoStatus = TodoStatus.TODO
    var createdDate: LocalDateTime = LocalDateTime.now()
    var lastModifiedDate: LocalDateTime = LocalDateTime.now()
    var isDeleted: Boolean = false
}

enum class TodoStatus(val description: String) {
    TODO("할일"),
    DONE("완료")
}

class UserAccept(
        var userId: String,
        var accept: AcceptStatus = AcceptStatus.REQUEST
)

enum class AcceptStatus(val description: String) {
    REQUEST("요청"),
    CANCEL("요청취소"),
    ACCEPT("승인"),
    REJECT("거절")
}