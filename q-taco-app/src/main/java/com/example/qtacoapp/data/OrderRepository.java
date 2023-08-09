package com.example.qtacoapp.data;

import com.example.qtacoapp.domain.tacoorder.TacoOrder;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface OrderRepository extends CrudRepository<TacoOrder, Long> {

    public Optional<TacoOrder> findTacoOrderByToken(String token);


}
