package com.dragutin.loancalculator.domain;

import lombok.*;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class PeriodPaymentKey implements Serializable {
    @ManyToOne
    @JoinColumn(name = "calculation_id", nullable = false)
    @EqualsAndHashCode.Exclude
    private Calculation calculation;

    private Integer paymentNumber;

}
