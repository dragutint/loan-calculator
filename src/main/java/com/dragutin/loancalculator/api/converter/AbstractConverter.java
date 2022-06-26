package com.dragutin.loancalculator.api.converter;

import com.dragutin.loancalculator.api.domain.ApiObject;
import com.dragutin.loancalculator.domain.abstraction.GeneralEntity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class AbstractConverter<A extends ApiObject, D extends GeneralEntity> {
    abstract A toDTO( D domainObj );

    public List<A> toDTO(Collection<D> list )
    {
        List<A> result = new ArrayList<>();
        list.forEach( obj -> result.add( toDTO( obj ) ) );
        return result;
    }
}
