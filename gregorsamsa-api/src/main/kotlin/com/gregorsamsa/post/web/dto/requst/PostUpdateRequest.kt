package com.gregorsamsa.post.web.dto.requst

import com.fasterxml.jackson.annotation.JsonFormat
import com.gregorsamsa.core.post.PostStatus
import java.time.LocalDateTime
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class PostUpdateRequest(
    @field:NotBlank
    val title: String,

    val content: String?,

    @field:NotNull
    val status: PostStatus,

    @field:JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", shape = JsonFormat.Shape.STRING)
    val dueDateTime: LocalDateTime?,
)