package com.example.stacopaymentkotlin

import jakarta.servlet.http.HttpSession
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.Pattern
import org.hibernate.validator.constraints.CreditCardNumber
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.SessionAttribute
import org.springframework.web.bind.annotation.SessionAttributes
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import org.springframework.web.servlet.view.RedirectView
import java.math.BigDecimal
import java.util.Currency

@SpringBootApplication
class STacoPaymentKotlinApplication

fun main(args: Array<String>) {
    runApplication<STacoPaymentKotlinApplication>(*args)
}



@Controller
//@SessionAttributes("successUrl")
class PaymentController() {

    @GetMapping("/masterCard")
    fun showPaymentInfo(
            sellerInfo: SellerInfo,
            price: Price,
            token: String,
            successUrl: String,
            httpSession: HttpSession,
            model: Model): String {

        httpSession.setAttribute("successUrl", successUrl)
        httpSession.setAttribute("token", token)

        model.addAttribute("sellerInfo", sellerInfo)
        model.addAttribute("price", price)
        return "paymentFormMasterCard"
    }

    @ModelAttribute(name = "paymentInfo")
    fun paymentInfo(): PaymentInfo {
        return PaymentInfo()
    }

    @GetMapping("/visa")
    fun showPaymentInfoVisa(sellerInfo: SellerInfo,
                            price: Price,
                            model: Model): String {

        model.addAttribute("sellerInfo", sellerInfo)
        model.addAttribute("price", price)
//        model.addAttribute("paymentInfo", PaymentInfo())
        return "paymentFormVisa"
    }

    @PostMapping("/pay")
    fun processPayment(paymentInfo: PaymentInfo,
                       @SessionAttribute successUrl: String,
                       @SessionAttribute token: String,
                        redirectAttributes: RedirectAttributes
    ): RedirectView {

        println("PaymentInfo: $paymentInfo")

        redirectAttributes.addAttribute("token", token)

        return RedirectView(successUrl)
    }

}

data class PaymentInfo(

        var ccHolder: String = "",
        @CreditCardNumber var ccNumber: String = "",
        @Pattern(regexp = "\\d{2}\\/\\d{2}") var ccExpire: String = "",
        @Min(0) @Max(999) var ccCCV: Int = 0)


data class SellerInfo(val id: String, val name: String)

@Component
class StringToSellerInfoConverter : Converter<String, SellerInfo> {
    override fun convert(source: String): SellerInfo? {

        val split = source.split("+")
        return SellerInfo(split[0], split[1])
    }

}

@Component
class SellerInfoToStringConverter : Converter<SellerInfo, String> {
    override fun convert(source: SellerInfo): String? {
        return "%s+%s".format(source.id, source.name)
    }

}

data class Price(val amount: BigDecimal, val currency: Currency)

@Component
class StringToPriceConverter : Converter<String, Price> {
    override fun convert(source: String): Price? {
        val split = source.split("+")
        return Price(split[1].toBigDecimal(), Currency.getInstance(split[0]));
    }

}

@Component
class PriceToStringConverter : Converter<Price, String> {
    override fun convert(source: Price): String? {
        return "%s+%s".format(source.currency, source.amount)
    }

}
