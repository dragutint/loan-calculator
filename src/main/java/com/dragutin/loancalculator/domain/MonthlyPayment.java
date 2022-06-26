package com.dragutin.loancalculator.domain;

import com.dragutin.loancalculator.domain.abstraction.CompositeGeneralEntity;
import com.dragutin.loancalculator.math.util.MathRoundingUtility;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.PrePersist;
import java.util.Objects;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class MonthlyPayment extends CompositeGeneralEntity<MonthlyPaymentKey> implements Comparable<MonthlyPayment>{

    private Double paymentAmount;

    private Double principalAmount;

    private Double interestAmount;

    private Double balanceOwed;

    @PrePersist
    private void prePersist() {
        if(Objects.nonNull(principalAmount) && Objects.nonNull(interestAmount))
            paymentAmount = MathRoundingUtility.twoDecimals(principalAmount + interestAmount);
    }

    @Override
    public int compareTo(MonthlyPayment mp) {
        return this.getId().getPaymentNumber() - mp.getId().getPaymentNumber();
    }
}