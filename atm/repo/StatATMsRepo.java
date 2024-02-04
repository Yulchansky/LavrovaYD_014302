package com.atm.repo;

import com.atm.model.StatATMs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatATMsRepo extends JpaRepository<StatATMs, Long> {

}
