package com.turkcell.customerservice.services.abstracts;

import com.turkcell.customerservice.entities.dtos.CustomerAddRequest;
import com.turkcell.customerservice.entities.dtos.CustomerAddResponse;
import com.turkcell.customerservice.entities.dtos.CustomerUpdateRequest;
import com.turkcell.customerservice.entities.dtos.GetCustomerDto;
import java.util.List;

public interface CustomerService {

  CustomerAddResponse register(CustomerAddRequest request);

  void delete(int id);

  GetCustomerDto update(int id, CustomerUpdateRequest request);

  GetCustomerDto getById(int id);

  List<GetCustomerDto> getAll();

  double balanceUp(int customerId, double balance);

  double balanceDown(int customerId, double balance);

  double getBalanceByCustomerId(int customerId);
}
