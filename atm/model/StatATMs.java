package com.atm.model;

import com.atm.model.enums.CurrencyType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class StatATMs {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private ATMs ATM;

    private int sum;
    private float com;
    private String date;
    @Enumerated(EnumType.STRING)
    private CurrencyType type;

    public StatATMs(ATMs ATM, int sum, float com, CurrencyType type) {
        this.ATM = ATM;
        this.sum = sum;
        this.com = com;
        this.type = type;
        this.date = LocalDateTime.now().toString().substring(0, 10);
    }
}
