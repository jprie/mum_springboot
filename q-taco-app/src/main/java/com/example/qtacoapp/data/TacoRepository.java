package com.example.qtacoapp.data;

import com.example.qtacoapp.taco.Taco;
import org.springframework.data.repository.CrudRepository;

public interface TacoRepository extends CrudRepository<Taco, Long> {
}
