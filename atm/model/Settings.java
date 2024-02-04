package com.atm.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Settings {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;

    private int limitBYN;
    private int limitUSD;
    private int limitEUR;
    private int limitRUB;

    private float BYNtoUSD;
    private float BYNtoEUR;
    private float BYNtoRUB;

    private float USDtoBYN;
    private float USDtoEUR;
    private float USDtoRUB;

    private float EURtoBYN;
    private float EURtoUSD;
    private float EURtoRUB;

    private float RUBtoBYN;
    private float RUBtoUSD;
    private float RUBtoEUR;

    public Settings() {
        this.limitBYN = 1000;
        this.limitUSD = 1000;
        this.limitRUB = 1000;
        this.limitEUR = 1000;
        this.BYNtoUSD = 1;
        this.BYNtoEUR = 1;
        this.BYNtoRUB = 1;
        this.USDtoBYN = 1;
        this.USDtoEUR = 1;
        this.USDtoRUB = 1;
        this.EURtoBYN = 1;
        this.EURtoUSD = 1;
        this.EURtoRUB = 1;
        this.RUBtoBYN = 1;
        this.RUBtoUSD = 1;
        this.RUBtoEUR = 1;
    }
}
