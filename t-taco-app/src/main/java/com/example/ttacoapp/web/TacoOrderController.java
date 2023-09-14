package com.example.ttacoapp.web;

import com.example.ttacoapp.data.TacoOrderRepository;
import com.example.ttacoapp.domain.TacoOrder;
import com.example.ttacoapp.service.TacoOrderService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("tacoOrder")
public class TacoOrderController {

    private final TacoOrderService tacoOrderService;

    private final TacoOrderRepository tacoOrderRepository;

    // Konstruktor-Injection
    public TacoOrderController(TacoOrderService tacoOrderService, TacoOrderRepository tacoOrderRepository) {
        this.tacoOrderService = tacoOrderService;
        this.tacoOrderRepository = tacoOrderRepository;
    }

    @GetMapping("/current") // /orders/current
    public String showOrderForm() {
        return "tacoOrderForm";
    }

    @PostMapping() // orders
    public String processOrder(@ModelAttribute @Valid TacoOrder tacoOrder,
                               Errors errors, // TODO: warum ist Reihenfolge wichtig?
                               Model model,
                               SessionStatus sessionStatus) {

        if (errors.hasErrors()) {
            return "tacoOrderForm";
        }

        tacoOrderRepository.save(tacoOrder);
        // session leeren
        sessionStatus.setComplete();

        // Berechnung kommt ins Service layer
        var tacoNamesAndPrices = tacoOrderService.calculateTacoPrices(tacoOrder);
        var sumPrices = tacoOrderService.calculateSum(tacoOrder);

        model.addAttribute("tacoNamesAndPrices", tacoNamesAndPrices);
        model.addAttribute("sumPrices", sumPrices);

        log.info("Taco order with address {}", tacoOrder);
        return "orderSummary";
    }
}
