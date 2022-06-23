package com.dragutin.loancalculator.domain.abstraction;

import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

@Getter
@MappedSuperclass
public abstract class GeneralEntity<T extends Serializable>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected T id;

    @CreationTimestamp
    @Column(name = "created_at",
            updatable = false,
            nullable = false)
    protected Instant createdAt;

    @UpdateTimestamp
    @Column(name = "modified_at")
    protected Instant modifiedAt;

    @Column
    @Version
    protected int version;

}