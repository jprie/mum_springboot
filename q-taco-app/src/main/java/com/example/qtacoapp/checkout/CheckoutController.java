package com.example.qtacoapp.checkout;

import com.example.qtacoapp.data.OrderRepository;
import com.example.qtacoapp.shared.Price;
import com.example.qtacoapp.shared.SellerInfo;
import com.example.qtacoapp.tacoorder.OrderService;
import com.example.qtacoapp.tacoorder.TacoOrder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Currency;

@Slf4j
@Controller
@RequestMapping("/checkout")
@SessionAttributes("tacoOrder")
public class CheckoutController {
    private final OrderRepository orderRepository;

    private final OrderService orderService;
    private final PaymentService paymentService;

    public CheckoutController(OrderService orderService, PaymentService paymentService,
                              OrderRepository orderRepository) {
        this.orderService = orderService;
        this.paymentService = paymentService;
        this.orderRepository = orderRepository;
    }

    @ModelAttribute(name = "paymentProviderForm")
    public PaymentProviderForm paymentProviderForm() {
        return new PaymentProviderForm();
    }

    @GetMapping
    public String show_checkout_with_prices(@ModelAttribute TacoOrder tacoOrder,
                                            Model model) throws Exception {

        var pricedTacos = orderService.pricedTacos(tacoOrder);
        var sum = orderService.calculateSum(tacoOrder).orElseThrow(Exception::new);
        //hackish
        tacoOrder.setPrice(new Price(sum, Currency.getInstance("EUR")));
        model.addAttribute("customer", tacoOrder.getCustomer());
        model.addAttribute("tacos", pricedTacos);
        model.addAttribute("sum", sum);
        model.addAttribute("paymentProviders", PaymentProvider.allProviders());

        return "checkout";
    }

    @PostMapping
    public RedirectView processCheckout(PaymentProviderForm paymentProviderForm,
                                  @ModelAttribute TacoOrder tacoOrder,
                                  RedirectAttributes redirectAttributes)  {

        var paymentProvider = paymentService.processPaymentInfo(paymentProviderForm, tacoOrder);

        var url = paymentProvider.url();
        log.info("Price: {}", tacoOrder.getPrice());
        redirectAttributes.addAttribute("sellerInfo", new SellerInfo("taco_cloud_xyz", "TacoCloud"));
        redirectAttributes.addAttribute("price", tacoOrder.getPrice());
        redirectAttributes.addAttribute("token", tacoOrder.getToken());
        redirectAttributes.addAttribute("successUrl", "http://localhost:8080/checkout/success");

        // store order before payment
        orderService.storeOrder(tacoOrder);

        return new RedirectView(url);
    }

    @GetMapping("/success")
    @ResponseBody
    public String success(String token) {

        orderService.getOrderByToken(token);
        // find order for Token
        return "Success: " + token;
    }
}
