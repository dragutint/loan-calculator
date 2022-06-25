package com.dragutin.loancalculator.domain.abstraction;

import lombok.Getter;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;


@Getter
@MappedSuperclass
public abstract class SimpleGeneralEntity extends GeneralEntity<Integer> {
    @Id
    @GeneratedValue
    protected Integer id;
}
