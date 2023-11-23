package com.turkcell.customerservice.services.concretes;

import com.turkcell.customerservice.core.jwt.JwtService;
import com.turkcell.customerservice.entities.Customer;
import com.turkcell.customerservice.entities.dtos.auth.AuthenticationResponse;
import com.turkcell.customerservice.entities.dtos.auth.RegisterRequest;
import com.turkcell.customerservice.repositories.CustomerRepository;
import com.turkcell.customerservice.services.abstracts.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerManager implements CustomerService {

  private final CustomerRepository customerRepository;
  private final JwtService jwtService;
  private final PasswordEncoder passwordEncoder;

  @Override
  public AuthenticationResponse register(RegisterRequest request) {
    Customer customer =
        Customer.builder()
            .name(request.getName())
            .lastName(request.getLastName())
            .username(request.getUsername())
            .password(passwordEncoder.encode(request.getPassword()))
            .role("USER")
            .build();

    customerRepository.save(customer);
    String token = jwtService.generateToken(customer);
    return new AuthenticationResponse(token);
  }
}
