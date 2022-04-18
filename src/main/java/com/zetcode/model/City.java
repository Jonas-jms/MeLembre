package com.zetcode.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cities")
public class City {

    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="Cidade")
    private String name;
    
    @Column(name="Populacao")
    private int population;

    public City() {
    }

    public City(String name, int population) {

        this.name = name;
        this.population = population;
    }

    public Long getId() {

        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public int getPopulation() {

        return population;
    }

    public void setPopulation(int population) {

        this.population = population;
    }
}