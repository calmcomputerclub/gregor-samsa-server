package com.gregorsamsa.core.post

import com.gregorsamsa.core.author.Author
import com.gregorsamsa.core.common.BaseEntity
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity
internal class Post(
    @field:Column(nullable = false, columnDefinition = "varchar(255)")
    var title: String,

    @field:Column(nullable = true, columnDefinition = "text")
    var content: String? = null,

    @field:Column(nullable = true, columnDefinition = "varchar(30)")
    var status: PostStatus,

    @field:Column(nullable = true, columnDefinition = "datetime")
    var dueDateTime: LocalDateTime? = null,

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "authorId", insertable = false, updatable = false, nullable = true)
    var author: Author?,
): BaseEntity()