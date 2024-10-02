package com.job.finance.infrastructure.listeners

import com.fasterxml.jackson.databind.ObjectMapper
import com.job.finance.usecases.applications.submitted.ApplicationSubmittedDto
import com.job.finance.usecases.applications.submitted.ApplicationSubmittedUseCase
import kotlinx.coroutines.runBlocking
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.support.KafkaHeaders
import org.springframework.messaging.handler.annotation.Header
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Component

@Component
class KafkaEventListener(
    private val objectMapper: ObjectMapper,
    private val applicationSubmittedUseCase: ApplicationSubmittedUseCase
) {
    @KafkaListener(
        topics = ["\${kafka.topic.application}"],
        groupId = "\${kafka.group-id.finance}",
    )
    fun handle(
        @Payload message: Map<String, Any>,
        @Header(KafkaHeaders.RECEIVED_TOPIC) topic: String,
    ) {
        println("Received message: $message")
        val dto = objectMapper.convertValue(message, ApplicationSubmittedDto::class.java)

        runBlocking {
            applicationSubmittedUseCase.execute(dto)
        }
    }
}
