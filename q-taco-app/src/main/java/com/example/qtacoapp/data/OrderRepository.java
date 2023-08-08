package com.example.qtacoapp.data;

import com.example.qtacoapp.tacoorder.TacoOrder;
import org.h2.command.query.TableValueConstructor;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface OrderRepository extends CrudRepository<TacoOrder, Long> {

    public Optional<TacoOrder> getTacoOrderByToken(String token);
}
