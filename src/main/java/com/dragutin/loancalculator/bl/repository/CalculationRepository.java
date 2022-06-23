package com.dragutin.loancalculator.bl.repository;

import com.dragutin.loancalculator.domain.Calculation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CalculationRepository extends JpaRepository<Calculation, Integer> {

}
