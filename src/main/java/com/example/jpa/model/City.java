package com.example.jpa.model;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.TableGenerator;

@Entity
@NoArgsConstructor
public class City {
    @Id
    @TableGenerator(
            name="idgen",
            table="id_gen",
            pkColumnName="entity",
            pkColumnValue="city",
            valueColumnName="nextid",
            initialValue=0,
            allocationSize=1)
    @GeneratedValue(generator="idgen")
    private Long id;
}