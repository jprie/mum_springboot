package com.example.ttacoapp.data;

import com.example.ttacoapp.domain.Taco;
import org.springframework.data.repository.CrudRepository;

public interface TacoRepository extends CrudRepository<Taco, Long> {
}
