package com.gregorsamsa.post.service

import com.gregorsamsa.core.post.PostCommand
import com.gregorsamsa.exception.NotFoundException
import com.gregorsamsa.post.web.dto.response.PostViewResponse
import org.springframework.stereotype.Service

@Service
class PostQueryService(
    private val postCommand: PostCommand
) {
    fun getAllPost(): List<PostViewResponse> {
        val allPost = postCommand.getAllPost()

        return allPost.map {
            PostViewResponse.of(it)
        }
    }

    fun getPost(postId: Long): PostViewResponse {
        val post = postCommand.getPostOrNull(postId)
            ?: throw NotFoundException("post is null, postId:$postId")

        return PostViewResponse.of(post)
    }
}