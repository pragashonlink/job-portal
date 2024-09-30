package com.job.finance.domain.services

import com.job.finance.config.Constant.COMMISSION_PERCENTAGE
import com.job.finance.repositories.ApplicationRepository
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class CommissionCalculationService(
    private val applicationRepository: ApplicationRepository,
) {
    suspend fun calculate(jobId: Long): BigDecimal {
        val applications = applicationRepository.findAllByJobId(jobId)
        if (applications.isEmpty()) return BigDecimal.ZERO

        val expectedSalarySum = applications.sumOf { it.expectedSalary }
        val averageExpectedSalary = expectedSalarySum / applications.size.toBigDecimal()
        return averageExpectedSalary * COMMISSION_PERCENTAGE
    }
}