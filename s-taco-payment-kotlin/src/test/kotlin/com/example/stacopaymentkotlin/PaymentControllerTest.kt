package com.example.stacopaymentkotlin

import jakarta.servlet.http.HttpSession
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.math.BigDecimal
import java.util.*

@WebMvcTest
class PaymentControllerTest {

    @MockBean
    lateinit var httpSession: HttpSession

    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var priceToStringConverter: PriceToStringConverter

    @Autowired
    lateinit var sellerInfoToStringConverter: SellerInfoToStringConverter

    @Test
    fun test_show_payment_info() {
        val sellerInfo = SellerInfo("taco_cloud_xyz", "TacoCloud")
        val price = Price(BigDecimal("3.6"), Currency.getInstance("EUR"))
        mockMvc.perform(
                get("/masterCard")
                        .param("sellerInfo", sellerInfoToStringConverter.convert(sellerInfo))
                        .param("price", priceToStringConverter.convert(price))
                        .param("token", "abcdef")
                        .param("successUrl", "http://localhost:8080/checkout/success")

        )
                .andExpect(status().isOk)

    }
}