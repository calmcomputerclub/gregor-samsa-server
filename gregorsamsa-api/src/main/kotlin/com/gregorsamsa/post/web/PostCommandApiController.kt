package com.gregorsamsa.post.web

import com.gregorsamsa.post.service.PostCommandService
import com.gregorsamsa.post.web.dto.requst.PostCreateRequest
import com.gregorsamsa.post.web.dto.response.PostCreateResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping(API_PREFIX)
class PostCommandApiController(
    private val postCommandService: PostCommandService,
) {

    @PostMapping("/post")
    fun postCreate(
        @RequestBody @Valid request: PostCreateRequest
    ): ResponseEntity<PostCreateResponse> {
        val postId = postCommandService.create(
            title = request.title,
            content = request.content,
            status = request.status,
            dueDateTime = request.dueDateTime
        )

        return ResponseEntity(PostCreateResponse.of(postId), HttpStatus.CREATED)
    }
}