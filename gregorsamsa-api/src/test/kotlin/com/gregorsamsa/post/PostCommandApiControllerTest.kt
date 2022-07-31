package com.gregorsamsa.post

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.gregorsamsa.post.service.PostCommandService
import com.gregorsamsa.post.web.PostCommandApiController
import com.gregorsamsa.post.web.dto.PostStatusDto
import com.gregorsamsa.post.web.dto.requst.PostCreateRequest
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post
import java.time.LocalDateTime

@WebMvcTest(PostCommandApiController::class)
class PostCommandApiControllerTest {
    @Autowired
    private lateinit var mockMvc: MockMvc

    private lateinit var objectMapper: ObjectMapper

    @MockkBean
    private lateinit var postCommandService: PostCommandService

    @BeforeEach
    fun setUp() {
        objectMapper = ObjectMapper()
        objectMapper.registerModule(JavaTimeModule())
    }

    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class `Post 등록 테스트` {
        @Test
        fun `Post Create 요청 200 OK`() {
            //given
            val request = PostCreateRequest(
                title = "test-title",
                content = "test-content",
                status = PostStatusDto.NOT_STARTED,
                dueDateTime = LocalDateTime.now()
            )

            val requestBody = objectMapper.writeValueAsString(request)
            val postId: Long = 1

            every { postCommandService.create(any(), any(), any(), any()) } returns postId

            //when
            val response = mockMvc.post("/v1/api/post") {
                contentType = MediaType.APPLICATION_JSON
                content = requestBody
            }

            //then
            response.andExpect {
                status { isCreated() }
                jsonPath("$['postId']") { value(postId) }
            }.andDo { print() }
        }

        @Test
        fun `title 필수값 없을 때 400 BadRequest`() {
            //given
            val request = PostCreateRequest(
                title = "",
                content = "test-content",
                status = PostStatusDto.NOT_STARTED,
                dueDateTime = LocalDateTime.now()
            )

            val requestBody = objectMapper.writeValueAsString(request)

            every { postCommandService.create(any(), any(), any(), any()) } returns 1

            //when
            val response = mockMvc.post("/v1/api/post") {
                contentType = MediaType.APPLICATION_JSON
                content = requestBody
            }

            //then
            response.andExpect {
                status { isBadRequest() }
            }.andDo { print() }

        }
    }
}