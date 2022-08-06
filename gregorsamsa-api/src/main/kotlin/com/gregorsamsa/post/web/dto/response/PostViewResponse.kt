package com.gregorsamsa.post.web.dto.response

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonFormat.Shape.*
import com.gregorsamsa.core.author.Author
import com.gregorsamsa.core.post.PostStatus
import com.gregorsamsa.core.post.PostVO
import java.time.LocalDateTime

data class PostViewResponse private constructor(
    val title: String,
    val content: String?,
    val status: PostStatus,
    @field:JsonFormat(shape = STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    val dueDateTime: LocalDateTime?,
    val author: Author? = null
) {
    companion object {
        fun of(post: PostVO) = PostViewResponse(
            title = post.title,
            content = post.content,
            status = post.status,
            dueDateTime = post.dueDateTime,
            author = post.author
        )
    }
}