package com.turkcell.rentalservice.repositories;

import com.turkcell.rentalservice.entities.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalRepository extends JpaRepository<Rental,Integer> {}
