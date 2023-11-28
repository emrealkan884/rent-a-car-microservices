package com.turkcell.customerservice.services.concretes;

import com.turkcell.customerservice.entities.Customer;
import com.turkcell.customerservice.entities.dtos.requests.CustomerAddRequest;
import com.turkcell.customerservice.entities.dtos.requests.CustomerUpdateRequest;
import com.turkcell.customerservice.entities.dtos.responses.CustomerAddResponse;
import com.turkcell.customerservice.entities.dtos.responses.CustomerGetResponse;
import com.turkcell.customerservice.entities.dtos.responses.CustomerUpdateResponse;
import com.turkcell.customerservice.repositories.CustomerRepository;
import com.turkcell.customerservice.services.abstracts.CustomerService;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerManager implements CustomerService {
  private final CustomerRepository customerRepository;
  private final MessageSource messageSource;

  @Override
  public CustomerAddResponse register(CustomerAddRequest request) {
    // customerWithSameEmailShouldNotExist(request.getEmail());
    Customer customer =
        Customer.builder()
            .name(request.getName())
            .email(request.getEmail())
            .username(request.getUsername())
            .lastName(request.getLastName())
            .password(request.getPassword())
            .balance(0)
            .build();
    customerRepository.save(customer);
    CustomerAddResponse customerAddResponse =
        CustomerAddResponse.builder()
            .id(customer.getId())
            .name(customer.getName())
            .email(customer.getEmail())
            .lastName(customer.getLastName())
            .username(customer.getUsername())
            .build();
    return customerAddResponse;
  }

  @Override
  public void delete(int id) {
    customerRepository.deleteById(id);
  }

  @Override
  public CustomerUpdateResponse update(int id, CustomerUpdateRequest request) {
    Customer customer = customerRepository.getReferenceById(id);
    customer.setName(request.getName());
    customer.setEmail(request.getEmail());
    customer.setPassword(request.getPassword());
    customer.setLastName(request.getLastName());
    customer.setUsername(request.getUsername());
    customerRepository.save(customer);
    CustomerUpdateResponse customerUpdateResponse =
        CustomerUpdateResponse.builder()
            .username(customer.getUsername())
            .name(customer.getName())
            .lastName(customer.getLastName())
            .email(customer.getEmail())
            .build();
    return customerUpdateResponse;
  }

  @Override
  public CustomerGetResponse getById(int id) {
    Customer customer = customerRepository.getReferenceById(id);
    CustomerGetResponse customerGetResponse =
        CustomerGetResponse.builder()
            .username(customer.getUsername())
            .name(customer.getName())
            .lastName(customer.getLastName())
            .build();
    return customerGetResponse;
  }

  @Override
  public List<CustomerGetResponse> getAll() {
    List<Customer> customers = customerRepository.findAll();
    List<CustomerGetResponse> customerGetResponses = new ArrayList<>();
    CustomerGetResponse customerGetResponse = new CustomerGetResponse();
    for (Customer customer : customers) {
      customerGetResponse.setUsername(customer.getUsername());
      customerGetResponse.setName(customer.getName());
      customerGetResponse.setLastName(customer.getLastName());
      customerGetResponses.add(customerGetResponse);
    }
    return customerGetResponses;
  }

  @Override
  public double getBalanceByCustomerId(int customerId) {
    Customer customer = customerRepository.getReferenceById(customerId);
    return customer.getBalance();
  }

  @Override
  public double balanceUp(int customerId, double balance) {
    Customer customer = customerRepository.getReferenceById(customerId);
    customer.setBalance(customer.getBalance() + balance);
    customer = customerRepository.save(customer);
    return customer.getBalance();
  }

  @Override
  public double balanceDown(int customerId, double balance) {
    Customer customer = customerRepository.getReferenceById(customerId);
    customer.setBalance(customer.getBalance() - balance);
    customer = customerRepository.save(customer);
    return customer.getBalance();
  }

  //  private void customerWithSameEmailShouldNotExist(String email) {
  //    // Aynı emaile sahip iki musteri olmamalı
  //    Customer customerWithSameEmail = customerRepository.findByEmail(email);
  //    if (customerWithSameEmail != null) {
  //      // Business kuralı hatası
  //      throw new BusinessException(
  //          messageSource.getMessage(
  //              "customerWithSameEmailShouldNotExist", null, LocaleContextHolder.getLocale()));
  //    }
  //  }
}
