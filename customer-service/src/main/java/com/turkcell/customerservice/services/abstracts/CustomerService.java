package com.turkcell.customerservice.services.abstracts;

import com.turkcell.customerservice.entities.dtos.CustomerAddRequest;
import com.turkcell.customerservice.entities.dtos.CustomerAddResponse;

public interface CustomerService {
  CustomerAddResponse register(CustomerAddRequest request);
}
