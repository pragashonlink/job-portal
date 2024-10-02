package com.job.finance.usecases.commissions.forecast

import com.job.finance.config.Constant.TARGET_CURRENCY
import com.job.finance.infrastructure.integrations.exchangerate.ExchangeRateClient
import com.job.finance.repositories.ClientForecastCommission
import com.job.finance.repositories.JobCommissionForecastRepository
import org.springframework.stereotype.Service

@Service
class CommissionForecastUseCase(
    private val jobCommissionForecastRepository: JobCommissionForecastRepository,
    private val exchangeRateClient: ExchangeRateClient
) {
    suspend fun execute(clientReferenceId: String): ClientForecastCommission {
        val commission = jobCommissionForecastRepository
            .getClientCommission(clientReferenceId)
        val exchangeRate = exchangeRateClient.getExchangeRate(
            baseCurrencyCode = commission.currencyCode.lowercase(),
            targetCurrencyCode = TARGET_CURRENCY.lowercase()
        )
        return commission.copy(totalCommission = commission.totalCommission * exchangeRate)
    }
}
