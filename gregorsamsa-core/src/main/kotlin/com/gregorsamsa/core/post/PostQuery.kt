package com.gregorsamsa.core.post

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@Transactional(readOnly = true)
class PostQuery(
    private val postRepository: PostRepository
) {
    fun getAllPost(): List<PostVO> {
        val posts = postRepository.findAll()

        return posts.map { PostVO.of(it) }
    }

    fun getPostOrNull(postId: Long): PostVO? {
        val post = postRepository.findByIdOrNull(postId)

        return post?.let { PostVO.of(it) }
    }
}