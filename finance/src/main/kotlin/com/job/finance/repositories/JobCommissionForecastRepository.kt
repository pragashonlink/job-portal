package com.job.finance.repositories

import kotlinx.coroutines.flow.Flow
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate
import org.springframework.stereotype.Component
import kotlinx.coroutines.reactive.asFlow
import java.math.BigDecimal

@Component
class JobCommissionForecastRepository(private val template: R2dbcEntityTemplate) {
    fun getClientCommission(clientReferenceId: String): Flow<ClientForecastCommission> {
        val query = """
            SELECT c.id AS clientId, c.name AS clientName, 
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
                    totalCommission = row.get("totalCommission", BigDecimal::class.java) ?: BigDecimal.ZERO
                )
            }
            .all()
            .asFlow()
    }
}

data class ClientForecastCommission(
    val clientId: Long,
    val clientName: String,
    val totalCommission: BigDecimal
)
