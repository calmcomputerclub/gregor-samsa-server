package com.gregorsamsa.post.web.dto.requst

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonFormat.*
import com.gregorsamsa.post.web.dto.PostStatusDto
import java.time.LocalDateTime
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class PostCreateRequest(
    @field:NotBlank
    val title: String,

    val content: String?,

    @field:NotNull
    val status: PostStatusDto,

    @field:JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", shape = Shape.STRING)
    val dueDateTime: LocalDateTime?,
)