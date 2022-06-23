package com.dragutin.loancalculator.domain;

import com.dragutin.loancalculator.domain.abstraction.GeneralEntity;
import lombok.*;

import javax.persistence.Entity;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Calculation extends GeneralEntity<Integer> {

    private Double loanAmount;

    private Double interestRate;

    private Integer loanTerm;

    private Double monthlyPayment;

    private Double totalInterestPaid;

}