package com.job.finance.usecases.commissions.forecast

import java.math.BigDecimal

data class CommissionForecastDto (
    val clientName: String,
    val forecastCommission: BigDecimal
)