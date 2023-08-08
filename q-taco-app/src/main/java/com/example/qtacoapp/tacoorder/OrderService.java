package com.example.qtacoapp.tacoorder;

import com.example.qtacoapp.checkout.PaymentProvider;
import com.example.qtacoapp.customer.Address;
import com.example.qtacoapp.customer.Customer;
import com.example.qtacoapp.customer.EmailAddress;
import com.example.qtacoapp.customer.PhoneNumber;
import com.example.qtacoapp.data.OrderRepository;
import com.example.qtacoapp.taco.Ingredient;
import com.example.qtacoapp.taco.Taco;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@Slf4j
@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void process(OrderForm orderForm, TacoOrder tacoOrder) {

        tacoOrder.setCustomer(Customer.builder()
                .firstName(orderForm.getFirstName())
                .lastName(orderForm.getLastName())
                .address(new Address(orderForm.getStreet(), orderForm.getStreetNr(), orderForm.getCity(), orderForm.getState(), orderForm.getZipCode(), orderForm.getCountry()))
                .email(new EmailAddress(orderForm.getEmail()))
                .phone(new PhoneNumber(orderForm.getPhonePrefix(), orderForm.getPhone()))
                .build()
        );

        log.info("Processed taco order {}", tacoOrder);

    }

    public List<PricedTaco> pricedTacos(TacoOrder tacoOrder) {
        var tacos = tacoOrder.getTacos();
        List<BigDecimal> prices = calculatePrices(tacoOrder);
        if (tacos.size() != prices.size()) return List.of();
        return IntStream.range(0, tacoOrder.getTacos().size())
                .mapToObj(index -> new PricedTaco(
                        tacos.get(index).getName(), prices.get(index)))
                .toList();

    }

    public List<BigDecimal> calculatePrices(TacoOrder tacoOrder) {

        var tacos = tacoOrder.getTacos();
        return tacos.stream()
                .map(OrderService::calculatePrices)
                .flatMap(Optional::stream)
                .toList();

    }

    private static Optional<BigDecimal> calculatePrices(Taco taco) {

        return taco.getIngredients().stream().map(Ingredient::getPrice).reduce(BigDecimal::add);
    }

    public Optional<BigDecimal> calculateSum(TacoOrder tacoOrder) {
        var tacos = tacoOrder.getTacos();
        return tacos.stream()
                .map(OrderService::calculatePrices)
                .flatMap(Optional::stream)
                .reduce(BigDecimal::add);
    }

    public void storePaymentInfo(PaymentProvider provider, TacoOrder currentOrder) {

        currentOrder.getCustomer().setPaymentProvider(provider);
    }

    public void storeOrder(TacoOrder tacoOrder) {

        orderRepository.save(tacoOrder);
    }

    public void getOrderByToken(String token) {


    }
}
