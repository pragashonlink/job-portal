package com.job.finance.usecases.applications.submitted

import com.job.finance.domain.exceptions.InvalidJobReferenceException
import com.job.finance.entities.ApplicationEntity
import com.job.finance.repositories.ApplicationRepository
import com.job.finance.repositories.JobRepository
import com.job.finance.domain.services.CommissionCalculationService
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.time.Instant

@Component
class ApplicationSubmittedUseCase(
    private val applicationRepository: ApplicationRepository,
    private val jobRepository: JobRepository,
    private val commissionCalculationService: CommissionCalculationService
) {
    @Transactional
    suspend fun execute(request: ApplicationSubmittedDto) {
        val job = jobRepository.findByJobReferenceId(request.jobReferenceId)
            ?: throw InvalidJobReferenceException("Job reference id is not valid ${request.jobReferenceId}")
        val jobId = job.id!!
        applicationRepository.save(ApplicationEntity(
            jobId = jobId,
            applicantReferenceId = request.applicationReferenceId,
            expectedSalary = request.expectedSalary,
            createdAt = Instant.now()
        ))
        val commissionForJob = commissionCalculationService.calculate(jobId)
        jobRepository.save(job.copy(totalCommission = commissionForJob))
    }

}