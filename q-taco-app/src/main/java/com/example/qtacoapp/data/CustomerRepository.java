package com.example.qtacoapp.data;

import com.example.qtacoapp.domain.customer.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
}
