package com.job.finance

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@ConfigurationPropertiesScan
@SpringBootApplication
class FinanceApplication

fun main(args: Array<String>) {
	runApplication<FinanceApplication>(*args)
}
