//package com.job.finance.infrastructure.integrations.exchangerate
//
//import io.mockk.coEvery
//import io.mockk.every
//import io.mockk.mockk
//import kotlinx.coroutines.runBlocking
//import org.junit.jupiter.api.Assertions.*
//import org.junit.jupiter.api.Test
//import org.springframework.web.reactive.function.client.WebClient
//import org.springframework.web.reactive.function.client.awaitExchange
//import reactor.core.publisher.Mono
//
//class ExchangeRateClientTest {
//    private val webClient = mockk<WebClient>()
//    private val requestHeadersUriSpec = mockk<WebClient.RequestHeadersUriSpec<*>>()
//    private val requestHeadersSpec = mockk<WebClient.RequestHeadersSpec<*>>()
//    private val exchangeRateClient = ExchangeRateClient(webClient)
//
//    @Test
//    fun `fetchData should return expected data`() = runBlocking {
//        // Mocking the WebClient response
//        val expectedResponse = "Expected Response"
//        val uri = "http://example.com/data"
//
//        // Mocking the WebClient's behavior
//        every { webClient.get() } returns requestHeadersUriSpec
//        every { requestHeadersUriSpec.uri(uri) } returns requestHeadersUriSpec
////        every { requestHeadersUriSpec.awaitExchange() } returns
//
//        coEvery { requestHeadersSpec.awaitExchange(any()) } returns Mono.just(expectedResponse)
//        coEvery { webClientBuilder.bodyToMono(String::class.java) } returns Mono.just(expectedResponse)
//
//        // Call the method under test
//        val result = exchangeRateClient.getExchangeRate("jpy")
//
//        // Assertions
//        assertEquals(expectedResponse, result)
//    }
//}