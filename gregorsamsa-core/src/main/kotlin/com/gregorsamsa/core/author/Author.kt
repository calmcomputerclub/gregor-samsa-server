package com.gregorsamsa.core.author

import com.gregorsamsa.core.common.BaseEntity
import javax.persistence.Column
import javax.persistence.Entity

@Entity
class Author(
    @field:Column(nullable = false, columnDefinition = "varchar(50)")
    val nickname: String,
    @field:Column(nullable = false, columnDefinition = "varchar(255)")
    var password: String,
): BaseEntity()