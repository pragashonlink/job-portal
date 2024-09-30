package com.job.finance.repositories

import com.job.finance.entities.JobEntity
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface JobRepository : CoroutineCrudRepository<JobEntity, Long> {
    suspend fun findByJobReferenceId(jobReferenceId: String): JobEntity?
}