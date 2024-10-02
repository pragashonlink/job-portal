package com.job.finance.infrastructure.integrations.exchangerate

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.web.reactive.function.client.WebClient

@ConfigurationProperties("integrations.exchange-rate")
@EnableConfigurationProperties(ExchangeRateConfig::class)
data class ExchangeRateConfig(
    val baseUrl: String,
) {
    @Bean("exchangeRateWebClient")
    fun webClient(): WebClient {
        return WebClient.builder()
            .baseUrl(baseUrl)
            .build()
    }
}