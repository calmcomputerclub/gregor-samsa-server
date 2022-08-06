package com.gregorsamsa.post

import com.gregorsamsa.core.post.PostStatus
import com.gregorsamsa.core.post.PostVO
import com.gregorsamsa.exception.NotFoundException
import com.gregorsamsa.post.service.PostQueryService
import com.gregorsamsa.post.web.API_PREFIX
import com.gregorsamsa.post.web.PostQueryApiController
import com.gregorsamsa.post.web.dto.response.PostViewResponse
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@WebMvcTest(PostQueryApiController::class)
class PostQueryApiControllerTest {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockkBean
    private lateinit var postQueryService: PostQueryService

    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class `post 개별 조회`() {
        @Test
        fun `200OK`() {
            //given
            val responseDto = PostViewResponse.of(
                PostVO.of(
                    title = "title",
                    content = "content",
                    status = PostStatus.NOT_STARTED,
                    dueDateTime = LocalDateTime.now()
                )
            )

            every { postQueryService.getPost(any()) } returns responseDto

            //when
            val response = mockMvc.get("$API_PREFIX/post/{postId}", 1)

            //then
            response.andExpect {
                status { isOk() }
                jsonPath("$['title']") { value(responseDto.title) }
                jsonPath("$['content']") { value(responseDto.content) }
                jsonPath("$['status']") { value("${responseDto.status}") }
                jsonPath("$['dueDateTime']") { value(responseDto.dueDateTime?.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))) }
                jsonPath("$['author']") { value(null) }

            }.andDo { print() }
        }

        @Test
        fun `데이터 없으면 404 NotFound`() {
            //given
            every { postQueryService.getPost(any()) } throws NotFoundException("error message")

            //when
            val response = mockMvc.get("$API_PREFIX/post/{postId}", 1)

            //then
            response.andExpect {
                status { isNotFound() }
            }.andDo { print() }
        }
    }

    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class `post 전체 조회`() {
        @Test
        fun `200OK`() {
            //given
            val responseDto = PostViewResponse.of(
                PostVO.of(
                    title = "title",
                    content = "content",
                    status = PostStatus.NOT_STARTED,
                    dueDateTime = LocalDateTime.now()
                )
            )

            every { postQueryService.getAllPost() } returns listOf(responseDto)

            //when
            val response = mockMvc.get("$API_PREFIX/post")

            //then
            response.andExpect {
                status { isOk() }
                jsonPath("$[0].title") { value(responseDto.title) }
                jsonPath("$[0].content") { value(responseDto.content) }
                jsonPath("$[0].status") { value("${responseDto.status}") }
                jsonPath("$[0].dueDateTime") { value(responseDto.dueDateTime?.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))) }
                jsonPath("$[0].author") { value(null) }
            }.andDo { print() }
        }
    }
}