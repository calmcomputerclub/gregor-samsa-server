package com.gregorsamsa.post.web.dto.response

import com.fasterxml.jackson.annotation.JsonFormat
import com.gregorsamsa.core.author.Author
import com.gregorsamsa.core.post.PostStatus
import com.gregorsamsa.core.post.PostVO
import java.time.LocalDateTime

data class PostUpdateResponse private constructor(
    val postId: Long,
    val title: String,
    val content: String?,
    val status: PostStatus?,
    @field:JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    val dueDateTime: LocalDateTime?,
    val author: Author?
) {
    companion object {
        fun of(post: PostVO) = PostUpdateResponse(
            postId = post.id!!,
            title = post.title,
            content = post.content,
            status = post.status,
            dueDateTime = post.dueDateTime,
            author = null,
        )

    }
}