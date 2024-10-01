package com.job.finance.domain.services

import com.job.finance.entities.ApplicationEntity
import com.job.finance.repositories.ApplicationRepository
import io.kotest.matchers.shouldBe
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.math.BigDecimal
import java.time.Instant
import java.util.*

@ExtendWith(MockKExtension::class)
class ForecastCommissionCalculationServiceTest {
    @MockK
    private lateinit var applicationRepository: ApplicationRepository
    private lateinit var forecastCommissionCalculationService: ForecastCommissionCalculationService

    @BeforeEach
    fun setUp() {
        forecastCommissionCalculationService = ForecastCommissionCalculationService(applicationRepository)
    }

    @AfterEach
    fun resetAndVerify() {
        clearAllMocks()
    }

    @Test
    fun `should return zero commission when job applications are empty`() {
        runTest {
            coEvery { applicationRepository.findAllByJobId(any()) } coAnswers { Collections.emptyList() }
            val result = forecastCommissionCalculationService.calculate(10)
            result shouldBe BigDecimal.ZERO
        }
    }

    @Test
    fun `should return calculated commission when job applications are found`() {
        runTest {
            val applications = listOf(
                ApplicationEntity(
                    id = Long.MIN_VALUE,
                    jobId = Long.MIN_VALUE,
                    applicantReferenceId = "application reference id",
                    expectedSalary = BigDecimal("150000"),
                    createdAt = Instant.now()
                ),
                ApplicationEntity(
                    id = Long.MAX_VALUE,
                    jobId = Long.MIN_VALUE,
                    applicantReferenceId = "application reference id 2",
                    expectedSalary = BigDecimal("160000"),
                    createdAt = Instant.now()
                ),
            )
            coEvery { applicationRepository.findAllByJobId(any()) } coAnswers { applications }
            val result = forecastCommissionCalculationService.calculate(10)
            result shouldBe BigDecimal("15500.0")
        }
    }
}