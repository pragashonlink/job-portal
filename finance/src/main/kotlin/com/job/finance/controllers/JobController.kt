package com.job.finance.controllers

import com.job.finance.controllers.error.ErrorApiResponse
import com.job.finance.usecases.jobs.create.CreateJobRequest
import com.job.finance.usecases.jobs.create.CreateJobResponse
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/jobs")
@Validated
class JobController {

    @Operation(summary = "Create job advert")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "201",
                description = "Job advert successfully created",
                content = [
                    Content(
                        schema = Schema(implementation = CreateJobResponse::class),
                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                    ),
                ],
            ),
            ApiResponse(
                responseCode = "400",
                description = "Invalid request details",
                content = [
                    Content(
                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                        schema = Schema(implementation = ErrorApiResponse::class),
                    ),
                ],
            ),
            ApiResponse(
                responseCode = "401",
                description = "invalid authentication",
                content = [
                    Content(
                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                        schema = Schema(implementation = ErrorApiResponse::class),
                    ),
                ],
            ),
            ApiResponse(responseCode = "500", description = "Internal server error"),
        ],
    )
    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping
    fun createJobs(
        @RequestHeader("X-Api-Key") authorizationHeader: String,
        @RequestBody createJobRequest: CreateJobRequest
    ): List<String> {
        return listOf("Job 1", "Job 2", "Job 3")
    }
}