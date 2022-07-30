package com.gregorsamsa.core.post

import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class PostCommand(
    private val postRepository: PostRepository,
) {
    fun create(
        title: String,
        content: String?,
        status: String,
        dueDateTime: LocalDateTime?,
    ): Long {
        val post = Post(
            title = title,
            content = content,
            status = PostStatus.valueOf(status),
            dueDateTime = dueDateTime,
            author = null
        )

        val saved = postRepository.save(post)

        return saved.id!!
    }
}