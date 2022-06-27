package com.dragutin.loancalculator.bl.repository;

import com.dragutin.loancalculator.domain.PeriodPayment;
import com.dragutin.loancalculator.domain.PeriodPaymentKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeriodPaymentRepository extends JpaRepository<PeriodPayment, PeriodPaymentKey> {

}
