package com.job.finance.controllers.error

import com.job.finance.domain.ErrorCode
import com.job.finance.domain.ErrorCode.INVALID_REQUEST
import com.job.finance.domain.ErrorCode.INVALID_API_KEY
import org.springframework.http.HttpStatus.BAD_REQUEST
import org.springframework.http.HttpStatus.UNAUTHORIZED
import org.springframework.http.ResponseEntity
/**
 * We want to keep the mapping logic separate from [ErrorCode], as it should be the Controller layer's responsibility
 * to map [ErrorCode] into an API response
 */

object ErrorCodeToErrorApiResponse {
    private val errorCodeToHttpStatus =
        mapOf(
            INVALID_REQUEST to BAD_REQUEST,
            INVALID_API_KEY to UNAUTHORIZED
        )

    init {
        val errorCodeNotMapped = ErrorCode.entries.toSet() - errorCodeToHttpStatus.keys.toSet()
        if (errorCodeNotMapped.isNotEmpty()) {
            throw IllegalStateException("Mapping not available for the following error codes: $errorCodeNotMapped")
        }
    }

    fun map(
        errorCode: ErrorCode,
        errors: Collection<String> = emptyList(),
    ): ResponseEntity<ErrorApiResponse> {
        val httpStatus =
            errorCodeToHttpStatus[errorCode]
                ?: throw IllegalArgumentException("Could not map $this to ResponseEntity")
        return ResponseEntity.status(httpStatus).body(ErrorApiResponse(errorCode, errors))
    }
}
