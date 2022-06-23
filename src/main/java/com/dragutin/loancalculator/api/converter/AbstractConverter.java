package com.dragutin.loancalculator.api.converter;

import com.dragutin.loancalculator.api.domain.ApiObject;
import com.dragutin.loancalculator.domain.abstraction.GeneralEntity;

public abstract class AbstractConverter<A extends ApiObject, D extends GeneralEntity> {
    abstract A toDTO( D domainObj );

}
