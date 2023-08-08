package com.example.qtacoapp.tacoorder;

import com.example.qtacoapp.customer.PhonePrefix;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("tacoOrder")
public class OrderController {

    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @ModelAttribute(name = "phonePrefixes")
    public List<PhonePrefix> phonePrefixList() {
        return PhonePrefix.allPrefixes();
    }

    @ModelAttribute(name = "orderForm")
    public OrderForm orderForm() {
        return new OrderForm();
    }

    @GetMapping("/current")
    public String showCurrentOrder(@ModelAttribute TacoOrder tacoOrder) {
        log.info("Processing order: {}", tacoOrder);
        return "orderForm";
    }

    @PostMapping("/current")
    public String processOrder(@Valid OrderForm orderForm,
                               Errors errors,
                               @ModelAttribute TacoOrder tacoOrder) {

        if (errors.hasErrors()) {
            errors.getAllErrors().forEach(objectError -> log.error("Error validating order: {}", objectError));
            return "orderForm";
        }
        log.info("Process orderForm: {}", orderForm);
        log.info("Comment");
        orderService.process(orderForm, tacoOrder);

        return "redirect:/checkout";
    }



}
