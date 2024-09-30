package com.job.finance.usecases.jobs.create

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank

data class CreateJobResponse(
    val jobId: String,
)
