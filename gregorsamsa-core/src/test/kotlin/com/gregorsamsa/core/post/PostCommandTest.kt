package com.gregorsamsa.core.post

import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import io.mockk.spyk
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.time.LocalDateTime

@ExtendWith(MockKExtension::class)
internal class PostCommandTest {

    @MockK
    lateinit var postRepository: PostRepository

    lateinit var sut: PostCommand

    @BeforeEach
    fun setUp() {
        sut = PostCommand(
            postRepository = postRepository
        )
    }

    @Test
    fun `post 등록하고 id 반환한다`() {
        //given
        val post = spyk<Post>().also {
            it.title = "title"
            it.content = "content"
            it.status = PostStatus.NOT_STARTED
            it.dueDateTime = LocalDateTime.now()
        }

        every { post.id } returns 1
        every { postRepository.save(any()) } returns post

        //when
        val postId = sut.create(
            title = post.title,
            content = post.content,
            status = post.status.toString(),
            dueDateTime = post.dueDateTime,
        )

        assertThat(postId).isEqualTo(post.id)
    }
}