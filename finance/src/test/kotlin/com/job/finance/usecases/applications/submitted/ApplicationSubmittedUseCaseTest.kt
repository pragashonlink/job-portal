package com.job.finance.usecases.applications.submitted

import com.job.finance.domain.services.ForecastCommissionCalculationService
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
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.math.BigDecimal
import java.time.Instant

@ExtendWith(MockKExtension::class)
class ApplicationSubmittedUseCaseTest {
    @MockK
    private lateinit var applicationRepository: ApplicationRepository
    @MockK
    private lateinit var jobRepository: JobRepository
    @MockK
    private lateinit var forecastCommissionCalculationService: ForecastCommissionCalculationService
    private lateinit var applicationSubmittedUseCase: ApplicationSubmittedUseCase

    @BeforeEach
    fun setUp() {
        applicationSubmittedUseCase = ApplicationSubmittedUseCase(
            applicationRepository,
            jobRepository,
            forecastCommissionCalculationService
        )
    }

    @AfterEach
    fun resetAndVerify() {
        clearAllMocks()
    }

    @Test
    fun `should return zero commission when job applications are empty`() {
        runTest {
            val createdAt = Instant.now()
            val jobReferenceId = "job_reference_id"
            val applicationReferenceId = "application_reference_id"
            val jobEntitySlot = slot<JobEntity>()
            val applicationEntitySlot = slot<ApplicationEntity>()
            val expectedApplicationEntity = ApplicationEntity(
                jobId = 1,
                applicantReferenceId = applicationReferenceId,
                expectedSalary = BigDecimal("10000.0"),
                createdAt = createdAt
            )
            val expectedJobEntity = JobEntity(
                id = 1,
                clientId = 1,
                jobReferenceId = jobReferenceId,
                title = "Software Engineer",
                forecastCommission = BigDecimal("1000.0"),
                createdAt = createdAt
            )
            coEvery { forecastCommissionCalculationService.calculate(any()) } coAnswers { BigDecimal("1000.0") }
            coEvery { jobRepository.findByJobReferenceId(any()) } coAnswers {
                JobEntity(
                    id = 1,
                    clientId = 1,
                    jobReferenceId = jobReferenceId,
                    title = "Software Engineer",
                    forecastCommission = BigDecimal.ZERO,
                    createdAt = createdAt
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
            applicationEntitySlot.captured.copy(createdAt = createdAt) shouldBe expectedApplicationEntity
            jobEntitySlot.captured.copy(createdAt = createdAt) shouldBe expectedJobEntity
        }
    }
}