package com.job.finance.infrastructure.integrations.exchangerate

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.job.finance.utilities.MappingUtility.Companion.convertToMap
import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import java.math.BigDecimal

@Component
class ExchangeRateClient(
    @Qualifier("exchangeRateWebClient")
    private val webClient: WebClient,
    private val objectMapper: ObjectMapper
) {
    suspend fun getExchangeRate(baseCurrencyCode: String, targetCurrencyCode: String): BigDecimal {
        val endPoint = "/${baseCurrencyCode}.json"
        val response = webClient
                                .get()
                                .uri(endPoint)
                                .retrieve()
                                .bodyToMono(String::class.java)
                                .awaitSingle()
        val exchangeRateResponse: Map<String, Any> = objectMapper.readValue(response, object : TypeReference<Map<String, Any>>() {})
        val rates = convertToMap(exchangeRateResponse[baseCurrencyCode])
        val rate = rates[targetCurrencyCode].toString()

        return rate.toBigDecimal()
    }
}
