package com.turkcell.rentalservice.services.abstracts;

import com.turkcell.rentalservice.entities.dtos.requests.RentalUpdateRequest;
import com.turkcell.rentalservice.entities.dtos.requests.SubmitRentalDto;
import com.turkcell.rentalservice.entities.dtos.responses.RentalGetResponse;
import com.turkcell.rentalservice.entities.dtos.responses.RentalUpdateResponse;
import java.util.List;

public interface RentalService {
  String submitRental(SubmitRentalDto submitRentalDto);

  void delete(int id);

  RentalUpdateResponse update(int id, RentalUpdateRequest request);

  RentalGetResponse getById(int id);

  List<RentalGetResponse> getAll();
}
