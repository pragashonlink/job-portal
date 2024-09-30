package com.job.finance.entities

import org.springframework.data.relational.core.mapping.Table
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.LastModifiedDate
import java.math.BigDecimal
import java.time.Instant

@Table(name = "jobs")
data class JobEntity (
    @Id
    val id: Long? = null,
    val clientId: Long,
    val jobReferenceId: String,
    val title: String,
    val totalCommission: BigDecimal,
    val collectedCommission: BigDecimal,
    val createdAt: Instant,
    @LastModifiedDate
    val updatedAt: Instant? = null,
)