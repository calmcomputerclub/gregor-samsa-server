package com.gregorsamsa.post.web.dto.response

data class PostCreateResponse private constructor(
    val postId: Long
) {
    companion object {
        fun of(postId: Long) =
            PostCreateResponse(
                postId = postId
            )
    }
}