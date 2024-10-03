package com.job.finance.domain.services

import com.job.finance.config.Constant.COMMISSION_PERCENTAGE
import com.job.finance.infrastructure.repositories.ApplicationRepository
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class ForecastCommissionCalculationService(
    private val applicationRepository: ApplicationRepository,
) {
    suspend fun calculate(jobId: Long, numberOfVacancies: Int): BigDecimal {
        val applications = applicationRepository.findAllByJobId(jobId)
        if (applications.isEmpty()) return BigDecimal.ZERO

        val expectedSalarySum = applications.sumOf { it.expectedSalary }
        val averageExpectedSalary = expectedSalarySum / applications.size.toBigDecimal()
        return averageExpectedSalary * numberOfVacancies.toBigDecimal() * COMMISSION_PERCENTAGE
    }
}