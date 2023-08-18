package com.example.ttacoapp.data;

import com.example.ttacoapp.domain.TacoOrder;
import org.springframework.data.repository.CrudRepository;

public interface TacoOrderRepository extends CrudRepository<TacoOrder, Long> {
}
