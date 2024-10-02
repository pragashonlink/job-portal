package com.job.finance.repositories

import org.springframework.data.r2dbc.core.R2dbcEntityTemplate
import org.springframework.r2dbc.core.awaitOne
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import java.math.BigDecimal

@Component
class JobCommissionForecastRepository(private val template: R2dbcEntityTemplate) {
    suspend fun getClientCommission(clientReferenceId: String): ClientForecastCommission {
        val query = """
            SELECT c.id AS clientId, c.name AS clientName, c.currency_code AS currencyCode, 
                   COALESCE(SUM(j.forecast_commission), 0) AS totalCommission
            FROM clients c 
            LEFT JOIN jobs j ON c.id = j.client_id 
            WHERE c.client_reference_id = '$clientReferenceId'
            GROUP BY c.id
        """.trimIndent()

        return template.databaseClient
            .sql(query)
            .map { row ->
                ClientForecastCommission(
                    clientId = row.get("clientId", Number::class.java)?.toLong() ?: 0L,
                    clientName = row.get("clientName", String::class.java) ?: "",
                    currencyCode = row.get("currencyCode", String::class.java) ?: "",
                    totalCommission = row.get("totalCommission", BigDecimal::class.java) ?: BigDecimal.ZERO
                )
            }.awaitOne()
    }
}

data class ClientForecastCommission(
    val clientId: Long,
    val clientName: String,
    val currencyCode: String,
    val totalCommission: BigDecimal
)
