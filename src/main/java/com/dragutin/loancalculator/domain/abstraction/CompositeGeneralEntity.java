package com.dragutin.loancalculator.domain.abstraction;

import lombok.Data;

import javax.persistence.EmbeddedId;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;


@Data
@MappedSuperclass
public abstract class CompositeGeneralEntity<T extends Serializable> extends GeneralEntity<T> {
    @EmbeddedId
    protected T id;
}