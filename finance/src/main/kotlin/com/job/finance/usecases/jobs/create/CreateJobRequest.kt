package com.job.finance.usecases.jobs.create

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class CreateJobRequest(
    @field:Schema(
        description = "Role title",
        example = "Software Engineer",
        type = "String",
        required = true,
    )
    @field:Size(min = 10, max = 100, message = "Job title")
    @field:NotBlank(message = "Expect job title")
    val title: String,

    @field:Schema(
        description = "job description",
        example = """
           <h2>Job Overview:</h2><p>We are looking for a skilled Software Engineer to join our dynamic team. 
           The ideal candidate will have a passion for technology and a desire to innovate.</p>
           
           <h2>Responsibilities:</h2><ul>
           <li>Develop, test, and maintain software applications.</li>
           <li>Collaborate with cross-functional teams to define, design, and ship new features.</li>
           <li>Write clean, maintainable code and perform code reviews.</li>
           <li>Troubleshoot and debug applications.</li>
           <li>Stay up-to-date with emerging technologies and industry trends.</li></ul> 
        """,
        type = "String",
        required = true,
    )
    @field:NotBlank(message = "Expect job description")
    @field:Size(min = 100, max = 1000, message = "Describe the job role")
    val description: String,

    @field:Schema(
        description = "positions",
        example = "1",
        type = "Int",
        required = true,
    )
    @field:NotBlank(message = "Expect number of positions to fill")
    @field:Min(1, message = "Minimum number of positions")
    val numberOfPositions: Int,
)
