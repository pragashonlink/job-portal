package com.job.finance.domain.services

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
import java.util.*

@ExtendWith(MockKExtension::class)
class CommissionCalculationServiceTest {
    @MockK
    private lateinit var applicationRepository: ApplicationRepository
    private lateinit var commissionCalculationService: CommissionCalculationService

    @BeforeEach
    fun setUp() {
        commissionCalculationService = CommissionCalculationService(applicationRepository)
    }

    @AfterEach
    fun resetAndVerify() {
        clearAllMocks()
    }

    @Test
    fun `should return zero commission when job applications are empty`() {
        runTest {
            coEvery { applicationRepository.findAllByJobId(any()) } coAnswers { Collections.emptyList() }
            val result = commissionCalculationService.calculate(10)
            result shouldBe BigDecimal.ZERO
        }
    }
}