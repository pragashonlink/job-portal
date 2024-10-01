package com.job.finance.usecases.commissions.forecast

import com.job.finance.repositories.ClientForecastCommission
import com.job.finance.repositories.JobCommissionForecastRepository
import kotlinx.coroutines.flow.Flow
import org.springframework.stereotype.Service

@Service
class CommissionForecastUseCase(
    private val jobCommissionForecastRepository: JobCommissionForecastRepository
) {
    suspend fun execute(clientReferenceId: String): Flow<ClientForecastCommission> {
        return jobCommissionForecastRepository.getClientCommission(clientReferenceId)
        //fetch client
        //fetch job
        //sum all the commissions
    }
}
