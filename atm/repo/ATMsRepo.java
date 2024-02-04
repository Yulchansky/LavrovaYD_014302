package com.atm.repo;

import com.atm.model.ATMs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ATMsRepo extends JpaRepository<ATMs, Long> {
    List<ATMs> findAllByNameContainingAndSerialContaining(String name, String serial);
}
