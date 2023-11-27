package com.turkcell.rentalservice.services.abstracts;

import com.turkcell.rentalservice.entities.dtos.requests.RentalUpdateRequest;
import com.turkcell.rentalservice.entities.dtos.responses.GetRentalDto;
import java.util.List;

public interface RentalService {
  String submitRental(String inventoryCode, int customerId);

  void delete(int id);

  GetRentalDto update(int id, RentalUpdateRequest request);

  GetRentalDto getById(int id);

  List<GetRentalDto> getAll();
}
