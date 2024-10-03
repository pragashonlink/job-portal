package com.job.finance.infrastructure.repositories

import com.job.finance.infrastructure.entities.JobEntity
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface JobRepository : CoroutineCrudRepository<JobEntity, Long> {
    suspend fun findByJobReferenceId(jobReferenceId: String): JobEntity?
}