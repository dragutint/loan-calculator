package com.dragutin.loancalculator.bl.repository;

import com.dragutin.loancalculator.domain.MonthlyPayment;
import com.dragutin.loancalculator.domain.MonthlyPaymentKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonthlyPaymentRepository extends JpaRepository<MonthlyPayment, MonthlyPaymentKey> {

}
