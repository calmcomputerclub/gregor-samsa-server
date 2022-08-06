package com.gregorsamsa.post.service

import com.gregorsamsa.core.post.PostCommand
import com.gregorsamsa.post.web.dto.PostStatusDto
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class PostCommandService(
    private val postCommand: PostCommand
) {

    fun create(
        title: String,
        content: String?,
        status: PostStatusDto,
        dueDateTime: LocalDateTime?
    ): Long {
        return postCommand.create(
            title = title,
            content = content,
            status = status.name,
            dueDateTime = dueDateTime,
        )
    }
}