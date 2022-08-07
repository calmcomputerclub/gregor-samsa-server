package com.gregorsamsa.post.web

import com.gregorsamsa.post.service.PostQueryService
import com.gregorsamsa.post.web.dto.response.PostViewResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(API_PREFIX)
class PostQueryApiController(
    private val postQueryService: PostQueryService
) {

    @GetMapping("/post")
    fun getPosts(): ResponseEntity<List<PostViewResponse>> {
        val posts = postQueryService.getAllPost()

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(posts)
    }

    @GetMapping("/post/{id}")
    fun getPost(@PathVariable id: Long): ResponseEntity<PostViewResponse> {

        val post = postQueryService.getPost(id)

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(post)
    }
}