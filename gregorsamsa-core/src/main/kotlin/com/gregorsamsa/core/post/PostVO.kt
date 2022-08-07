package com.gregorsamsa.core.post

import com.gregorsamsa.core.author.Author
import java.time.LocalDateTime

class PostVO private constructor(
    val id: Long? = null,
    val title: String,
    val content: String?,
    val status: PostStatus,
    val dueDateTime: LocalDateTime?,
    val author: Author? = null,
) {
    companion object {
        fun of(
            id: Long? = null,
            title: String,
            content: String?,
            status: PostStatus,
            dueDateTime: LocalDateTime?,
            author: Author? = null
        ) = PostVO(
            id = id,
            title = title,
            content = content,
            status = status,
            dueDateTime = dueDateTime,
            author = author,
        )

        fun of(post: Post) = of(
            id = post.id,
            title = post.title,
            content = post.content,
            status = post.status,
            dueDateTime = post.dueDateTime,
            author = post.author
        )
    }
}