package com.gregorsamsa.post.service

import com.gregorsamsa.core.post.PostCommand
import com.gregorsamsa.core.post.PostQuery
import com.gregorsamsa.exception.NotFoundException
import com.gregorsamsa.post.web.dto.response.PostViewResponse
import org.springframework.stereotype.Service

@Service
class PostQueryService(
    private val postQuery: PostQuery
) {
    fun getAllPost(): List<PostViewResponse> {
        val allPost = postQuery.getAllPost()

        return allPost.map {
            PostViewResponse.of(it)
        }
    }

    fun getPost(postId: Long): PostViewResponse {
        val post = postQuery.getPostOrNull(postId)
            ?: throw NotFoundException("post is null, postId:$postId")

        return PostViewResponse.of(post)
    }
}