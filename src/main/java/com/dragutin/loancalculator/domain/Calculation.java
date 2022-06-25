package com.dragutin.loancalculator.domain;

import com.dragutin.loancalculator.domain.abstraction.SimpleGeneralEntity;
import lombok.*;
import org.springframework.util.CollectionUtils;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Calculation extends SimpleGeneralEntity {

    private Double loanAmount;

    private Double interestRate;

    private Integer loanTerm;

    private Double fixedMonthlyPayment;

    private Double totalInterestPaid;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "id.calculation")
    private Set<MonthlyPayment> monthlyPayments;

    public void addMonthlyPayment(MonthlyPayment monthlyPayment) {
        if(CollectionUtils.isEmpty(monthlyPayments))
            monthlyPayments = new HashSet<>();

        if(Objects.nonNull(monthlyPayment))
            this.monthlyPayments.add(monthlyPayment);
    }
}