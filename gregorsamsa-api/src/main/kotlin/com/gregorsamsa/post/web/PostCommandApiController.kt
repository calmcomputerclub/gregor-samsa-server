package com.gregorsamsa.post.web

import com.gregorsamsa.post.service.PostCommandService
import com.gregorsamsa.post.web.dto.requst.PostCreateRequest
import com.gregorsamsa.post.web.dto.requst.PostUpdateRequest
import com.gregorsamsa.post.web.dto.response.PostCreateResponse
import com.gregorsamsa.post.web.dto.response.PostUpdateResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
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

    @PutMapping("/post/{postId}")
    fun postUpdate(
        @PathVariable postId: Long,
        @RequestBody @Valid request: PostUpdateRequest,
    ): ResponseEntity<PostUpdateResponse> {

        val post = postCommandService.update(
            postId = postId,
            title = request.title,
            content = request.content,
            status = request.status,
            dueDateTime = request.dueDateTime
        )

        return ResponseEntity.status(HttpStatus.OK).body(post)
    }

    @DeleteMapping("/post/{postId}")
    fun postDelete(@PathVariable postId: Long): ResponseEntity<Nothing> {
        postCommandService.delete(postId)

        return ResponseEntity.noContent().build()
    }
}