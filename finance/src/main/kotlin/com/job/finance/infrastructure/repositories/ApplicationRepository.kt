package com.job.finance.infrastructure.repositories

import com.job.finance.infrastructure.entities.ApplicationEntity
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ApplicationRepository : CoroutineCrudRepository<ApplicationEntity, Long> {
    suspend fun findAllByJobId(jobId: Long): Collection<ApplicationEntity>
}