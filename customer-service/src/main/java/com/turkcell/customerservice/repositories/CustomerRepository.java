package com.turkcell.customerservice.repositories;

import com.turkcell.customerservice.entities.Customer;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
  Optional<Customer> findByUsername(String username);

  Customer findByEmail(String email);
}
