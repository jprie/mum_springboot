package com.example.ttacoapp.web;

import com.example.ttacoapp.domain.TacoOrder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("tacoOrder")
public class TacoOrderController {

    @GetMapping("/current") // /orders/current
    public String showOrderForm() {
        return "tacoOrderForm";
    }

    @PostMapping()
    public String processOrder(@ModelAttribute TacoOrder tacoOrder) {

        log.info("Taco order with address {}", tacoOrder);
        return "orderSummary";
    }
}
