package com.dragutin.loancalculator.domain;

import com.dragutin.loancalculator.domain.abstraction.CompositeGeneralEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.PrePersist;
import java.util.Objects;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class MonthlyPayment extends CompositeGeneralEntity<MonthlyPaymentKey> {

    private Double paymentAmount;

    private Double principalAmount;

    private Double interestAmount;

    private Double balanceOwed;

    @PrePersist
    private void prePersist() {
        if(Objects.nonNull(principalAmount) && Objects.nonNull(interestAmount))
            paymentAmount = principalAmount + interestAmount;
    }

}