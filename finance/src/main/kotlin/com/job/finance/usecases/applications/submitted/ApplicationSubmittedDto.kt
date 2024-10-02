package com.job.finance.usecases.applications.submitted

import java.math.BigDecimal

data class ApplicationSubmittedDto (
    val jobReferenceId: String,
    val applicationReferenceId: String,
    val expectedSalary: BigDecimal
)
