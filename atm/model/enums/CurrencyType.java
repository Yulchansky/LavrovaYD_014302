package com.atm.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

@AllArgsConstructor
@Getter
public enum CurrencyType implements GrantedAuthority {
    BYN("BYN"),
    USD("USD"),
    EUR("EUR"),
    RUB("RUB"),
    ;

    private final String name;

    @Override
    public String getAuthority() {
        return name();
    }
}

