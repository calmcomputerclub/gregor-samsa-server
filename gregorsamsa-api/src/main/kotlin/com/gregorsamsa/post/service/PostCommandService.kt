package com.gregorsamsa.post.service

import com.gregorsamsa.core.post.PostCommand
import com.gregorsamsa.core.post.PostStatus
import com.gregorsamsa.exception.NotFoundException
import com.gregorsamsa.post.web.dto.PostStatusDto
import com.gregorsamsa.post.web.dto.response.PostUpdateResponse
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class PostCommandService(
    private val postCommand: PostCommand,
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

    fun update(
        postId: Long,
        title: String,
        content: String?,
        status: PostStatus,
        dueDateTime: LocalDateTime?
    ): PostUpdateResponse {
        val post = postCommand.update(
            postId = postId,
            title = title,
            content = content,
            status = status,
            dueDateTime = dueDateTime
        ) ?: throw NotFoundException("post is null")

        return PostUpdateResponse.of(post)
    }

    fun delete(postId: Long) {
        postCommand.deleteById(postId)
    }
}