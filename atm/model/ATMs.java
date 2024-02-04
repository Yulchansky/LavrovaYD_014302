package com.atm.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ATMs {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;

    private String name;
    private float com;
    private String address;
    private String serial;
    private String file;

    private boolean status;

    private int BYN;
    private int USD;
    private int RUB;
    private int EUR;

    @OneToMany(mappedBy = "ATM", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Histories> histories;

    @OneToMany(mappedBy = "ATM", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<StatATMs> statATMs;

    public ATMs(String name, float com, String address, String serial) {
        this.name = name;
        this.com = com;
        this.address = address;
        this.serial = serial;
        status = false;
        this.BYN = 0;
        this.USD = 0;
        this.RUB = 0;
        this.EUR = 0;
    }

    public void update(String name, float com, String address, String serial) {
        this.name = name;
        this.com = com;
        this.address = address;
        this.serial = serial;
    }

    public int getTransactions() {
        return histories.size();
    }

    public int getTransactions(String date) {
        return statATMs.stream().reduce(0, (i, h) -> {
            if (h.getDate().equals(date)) {
                return i + 1;
            }
            return i;
        }, Integer::sum);
    }

    public int getStatATMsSum(String currency, String date) {
        return statATMs.stream().reduce(0, (i, s) -> {
            if (s.getType().name().equals(currency) && s.getDate().contains(date)) {
                return i + s.getSum();
            }
            return i;
        }, Integer::sum);
    }

    public float getStatATMsCom(String currency, String date) {
        return statATMs.stream().reduce(0f, (i, s) -> {
            if (s.getType().name().equals(currency) && s.getDate().contains(date)) {
                return i + s.getCom();
            }
            return i;
        }, Float::sum);
    }
}
