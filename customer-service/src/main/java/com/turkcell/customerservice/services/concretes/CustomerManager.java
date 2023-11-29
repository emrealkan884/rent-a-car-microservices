package com.turkcell.customerservice.services.concretes;

import com.turkcell.customerservice.core.exceptions.BusinessException;
import com.turkcell.customerservice.entities.Customer;
import com.turkcell.customerservice.entities.dtos.requests.CustomerAddRequest;
import com.turkcell.customerservice.entities.dtos.requests.CustomerUpdateRequest;
import com.turkcell.customerservice.entities.dtos.responses.CustomerAddResponse;
import com.turkcell.customerservice.entities.dtos.responses.CustomerGetResponse;
import com.turkcell.customerservice.entities.dtos.responses.CustomerUpdateResponse;
import com.turkcell.customerservice.repositories.CustomerRepository;
import com.turkcell.customerservice.services.abstracts.CustomerService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerManager implements CustomerService {
  private final CustomerRepository customerRepository;
  private final MessageSource messageSource;
  private final ModelMapper modelMapper;

  @Override
  public CustomerAddResponse register(CustomerAddRequest request) {
    customerWithSameEmailShouldNotExist(request.getEmail());
    Customer customerForAutoMapping = modelMapper.map(request, Customer.class);
    customerForAutoMapping = customerRepository.save(customerForAutoMapping);
    CustomerAddResponse customerAddResponse =
        modelMapper.map(customerForAutoMapping, CustomerAddResponse.class);
    return customerAddResponse;
  }

  @Override
  public void delete(int id) {
    customerRepository.deleteById(id);
  }

  @Override
  public CustomerUpdateResponse update(int id, CustomerUpdateRequest request) {
    Customer customer = customerRepository.getReferenceById(id);
    modelMapper.map(request, customer);
    customer = customerRepository.save(customer);

    CustomerUpdateResponse customerUpdateResponse =
        modelMapper.map(customer, CustomerUpdateResponse.class);
    return customerUpdateResponse;
  }

  @Override
  public CustomerGetResponse getById(int id) {
    Customer customer = customerRepository.getReferenceById(id);
    CustomerGetResponse customerGetResponse = modelMapper.map(customer, CustomerGetResponse.class);
    return customerGetResponse;
  }

  @Override
  public List<CustomerGetResponse> getAll() {
    List<Customer> customers = customerRepository.findAll();
    List<CustomerGetResponse> customerGetResponses =
        customers.stream().map(item -> modelMapper.map(item, CustomerGetResponse.class)).toList();
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

  private void customerWithSameEmailShouldNotExist(String email) {
    // Aynı emaile sahip iki musteri olmamalı
    Customer customerWithSameEmail = customerRepository.findByEmail(email);
    if (customerWithSameEmail != null) {
      // Business kuralı hatası
      throw new BusinessException(
          messageSource.getMessage(
              "customerWithSameEmailShouldNotExist", null, LocaleContextHolder.getLocale()));
    }
  }
}
