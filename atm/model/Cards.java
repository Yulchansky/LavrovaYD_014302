package com.atm.model;

import com.atm.model.enums.CurrencyType;
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
public class Cards {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;
    private String name;
    private String number;
    private String date;
    private String fio;
    private String pin;
    private String cvv;
    private float money;
    private boolean status;
    @Enumerated(EnumType.STRING)
    private CurrencyType currency;
    @ManyToOne(fetch = FetchType.LAZY)
    private Users owner;
    @OneToMany(mappedBy = "card", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Histories> histories;
    public Cards(String name, String number, String date, String fio, float money, Users owner, String pin,String cvv, CurrencyType currency) {
        this.name = name;
        this.number = number;
        this.date = date;
        this.fio = fio;
        this.money = money;
        this.owner = owner;
        this.pin = pin;
        this.cvv = cvv;
        this.currency = currency;
        this.status = true;
    }

    public String getNumber() {
        return number.substring(0, 4) + " " + number.substring(4, 8) + " " + number.substring(8, 12) + " " + number.substring(12, 16);
    }
}
