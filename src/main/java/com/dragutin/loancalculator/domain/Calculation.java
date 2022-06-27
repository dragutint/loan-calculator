package com.dragutin.loancalculator.domain;

import com.dragutin.loancalculator.domain.abstraction.SimpleGeneralEntity;
import lombok.*;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Calculation extends SimpleGeneralEntity {

    private Double loanAmount;

    private Double interestRate;

    private Integer numberOfPayments;

    private Double fixedPeriodPayment;

    @Enumerated(EnumType.STRING)
    private PaymentFrequencyEnum paymentFrequency;

    private Double totalInterestPaid;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "id.calculation")
    private Set<PeriodPayment> periodPayments;

    public Calculation(Double loanAmount, Double interestRate, Integer numberOfPayments, PaymentFrequencyEnum paymentFrequency) {
        super();
        this.loanAmount = loanAmount;
        this.interestRate = interestRate;
        this.numberOfPayments = numberOfPayments;
        this.paymentFrequency = paymentFrequency;
    }

    public void addPeriodPayment(PeriodPayment periodPayment) {
        if(CollectionUtils.isEmpty(periodPayments))
            periodPayments = new TreeSet<>();

        if(Objects.nonNull(periodPayment))
            this.periodPayments.add(periodPayment);
    }
}