package com.atm.repo;

import com.atm.model.Histories;
import com.atm.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoriesRepo extends JpaRepository<Histories, Long> {
    List<Histories> findAllByOwner(Users owner);

    List<Histories> findAllByCard_IdAndDateContaining(Long cardId, String date);
    List<Histories> findAllByATM_IdAndDateContaining(Long atmId, String date);
}
