package com.job.finance.usecases.applications.submitted

import com.job.finance.domain.services.CommissionCalculationService
import com.job.finance.entities.ApplicationEntity
import com.job.finance.entities.JobEntity
import com.job.finance.repositories.ApplicationRepository
import com.job.finance.repositories.JobRepository
import io.kotest.matchers.shouldBe
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coJustRun
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.slot
import kotlinx.coroutines.test.runTest
import org.apache.kafka.clients.producer.ProducerRecord
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.math.BigDecimal
import java.time.Instant
import java.util.*

@ExtendWith(MockKExtension::class)
class ApplicationSubmittedUseCaseTest {
    @MockK
    private lateinit var applicationRepository: ApplicationRepository
    @MockK
    private lateinit var jobRepository: JobRepository
    @MockK
    private lateinit var commissionCalculationService: CommissionCalculationService
    private lateinit var applicationSubmittedUseCase: ApplicationSubmittedUseCase

    @BeforeEach
    fun setUp() {
        applicationSubmittedUseCase = ApplicationSubmittedUseCase(
            applicationRepository,
            jobRepository,
            commissionCalculationService
        )
    }

    @AfterEach
    fun resetAndVerify() {
        clearAllMocks()
    }

    @Test
    fun `should return zero commission when job applications are empty`() {
        runTest {
            val jobReferenceId = "job_reference_id"
            val applicationReferenceId = "application_reference_id"
            val jobEntitySlot = slot<JobEntity>()
            val applicationEntitySlot = slot<ApplicationEntity>()
            val expectedApplicationEntity = ApplicationEntity(
                jobId = Long.MIN_VALUE,
                applicantReferenceId = applicationReferenceId,
                expectedSalary = BigDecimal("10000.0"),
                createdAt = Instant.now()
            )
            val expectedJobEntity = JobEntity(
                jobReferenceId = jobReferenceId,
                totalCommission = BigDecimal("500.0"),
                title = "Software Engineer",
                clientId = Long.MINo9n8LUE,
                collectedCommission =
                    ''
            )
            coEvery { commissionCalculationService.calculate(any()) } coAnswers { BigDecimal("10000.0") }
            coEvery { jobRepository.findByJobReferenceId(any()) } coAnswers {
                JobEntity(
                    id = Long.MIN_VALUE,
                    collectedCommission = BigDecimal.ZERO,
                    totalCommission = BigDecimal.ZERO,
                    jobReferenceId = jobReferenceId,
                    title = "Software Engineer",
                    createdAt = Instant.now(),
                    clientId = Long.MIN_VALUE
                )
            }
            coJustRun { applicationRepository.save(any()) }
            coJustRun { jobRepository.save(any()) }

            applicationSubmittedUseCase.execute(
                ApplicationSubmittedDto(
                    expectedSalary = BigDecimal("10000.0"),
                    jobReferenceId = jobReferenceId,
                    applicationReferenceId = applicationReferenceId
                )
            )

            coVerify(exactly = 1) {
                applicationRepository.save(capture(applicationEntitySlot))
                jobRepository.save(capture(jobEntitySlot))
            }
            applicationEntitySlot.captured.copy(createdAt = expectedApplicationEntity.createdAt) shouldBe expectedApplicationEntity
            jobEntitySlot.captured.copy(createdAt = jobEntitySlot.createdAt) shouldBe expectedApplicationEntity
        }
    }
}