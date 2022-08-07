package com.gregorsamsa.core.post

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Component
class PostCommand(
    private val postRepository: PostRepository,
) {
    @Transactional
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

    @Transactional
    fun update(
        postId: Long,
        title: String,
        content: String?,
        status: PostStatus,
        dueDateTime: LocalDateTime?
    ): PostVO? {
        val post = postRepository.findByIdOrNull(postId)
        post?.update(
            title = title,
            content = content,
            status = status,
            dueDateTime = dueDateTime
        )

        return post?.let { PostVO.of(it) }
    }
}