package com.job.finance.controllers

import com.job.finance.infrastructure.repositories.ClientForecastCommission
import com.job.finance.usecases.commissions.forecast.CommissionForecastUseCase
import io.swagger.v3.oas.annotations.Operation
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/commission_forecast")
@Validated
class CommissionForecastController(
    private val commissionForecastUseCase: CommissionForecastUseCase
) {
    @Operation(summary = "Display commission forecast from a give client")
    @GetMapping("/{clientReferenceId}",)
    suspend fun getCommissionForecastReport(
        @PathVariable clientReferenceId: String
    ): ClientForecastCommission {
        return commissionForecastUseCase.execute(clientReferenceId)
    }

}