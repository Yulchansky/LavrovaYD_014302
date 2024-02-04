package com.atm.model;

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
public class Histories {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;

    private float sum;
    private String date;

    @ManyToOne(fetch = FetchType.LAZY)
    private Users owner;
    @ManyToOne(fetch = FetchType.LAZY)
    private Cards card;
    @ManyToOne(fetch = FetchType.LAZY)
    private ATMs ATM;

    public Histories(float sum, Users owner, ATMs ATM, Cards card) {
        this.sum = sum;
        this.date = LocalDateTime.now().toString().substring(0, 10);
        this.owner = owner;
        this.ATM = ATM;
        this.card = card;
    }
}
