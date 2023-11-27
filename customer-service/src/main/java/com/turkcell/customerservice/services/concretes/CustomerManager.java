package com.turkcell.customerservice.services.concretes;

import com.turkcell.customerservice.entities.Customer;
import com.turkcell.customerservice.entities.dtos.CustomerAddRequest;
import com.turkcell.customerservice.entities.dtos.CustomerAddResponse;
import com.turkcell.customerservice.entities.dtos.CustomerUpdateRequest;
import com.turkcell.customerservice.entities.dtos.GetCustomerDto;
import com.turkcell.customerservice.repositories.CustomerRepository;
import com.turkcell.customerservice.services.abstracts.CustomerService;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerManager implements CustomerService {
  private final CustomerRepository customerRepository;

  @Override
  public CustomerAddResponse register(CustomerAddRequest request) {
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
  public GetCustomerDto update(int id, CustomerUpdateRequest request) {
    Customer customer = customerRepository.getReferenceById(id);
    customer.setName(request.getName());
    customer.setEmail(request.getEmail());
    customer.setPassword(request.getPassword());
    customer.setLastName(request.getLastName());
    customer.setUsername(request.getUsername());
    customerRepository.save(customer);
    GetCustomerDto getCustomerDto =
        GetCustomerDto.builder()
            .username(customer.getUsername())
            .name(customer.getName())
            .lastName(customer.getLastName())
            .build();
    return getCustomerDto;
  }

  @Override
  public GetCustomerDto getById(int id) {
    Customer customer = customerRepository.getReferenceById(id);
    GetCustomerDto getCustomerDto =
        GetCustomerDto.builder()
            .username(customer.getUsername())
            .name(customer.getName())
            .lastName(customer.getLastName())
            .build();
    return getCustomerDto;
  }

  @Override
  public List<GetCustomerDto> getAll() {
    List<Customer> customers = customerRepository.findAll();
    List<GetCustomerDto> getCustomerDtos = new ArrayList<>();
    GetCustomerDto getCustomerDto = new GetCustomerDto();
    for (Customer customer : customers) {
      getCustomerDto.setUsername(customer.getUsername());
      getCustomerDto.setName(customer.getName());
      getCustomerDto.setLastName(customer.getLastName());
      getCustomerDtos.add(getCustomerDto);
    }
    return getCustomerDtos;
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
}
