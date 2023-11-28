package com.turkcell.customerservice.services.abstracts;

import com.turkcell.customerservice.entities.dtos.requests.CustomerAddRequest;
import com.turkcell.customerservice.entities.dtos.requests.CustomerUpdateRequest;
import com.turkcell.customerservice.entities.dtos.responses.CustomerAddResponse;
import com.turkcell.customerservice.entities.dtos.responses.CustomerGetResponse;
import com.turkcell.customerservice.entities.dtos.responses.CustomerUpdateResponse;
import java.util.List;

public interface CustomerService {

  CustomerAddResponse register(CustomerAddRequest request);

  void delete(int id);

  CustomerUpdateResponse update(int id, CustomerUpdateRequest request);

  CustomerGetResponse getById(int id);

  List<CustomerGetResponse> getAll();

  double balanceUp(int customerId, double balance);

  double balanceDown(int customerId, double balance);

  double getBalanceByCustomerId(int customerId);
}
