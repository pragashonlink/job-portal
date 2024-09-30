package com.job.finance.controllers.error

import com.fasterxml.jackson.annotation.JsonInclude
import com.job.finance.domain.ErrorCode

data class ErrorApiResponse(
    val errorCode: ErrorCode,
    @JsonInclude(JsonInclude.Include.NON_EMPTY) val errors: Collection<String> = emptyList(),
)
