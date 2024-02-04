package com.atm.repo;

import com.atm.model.Users;
import com.atm.model.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersRepo extends JpaRepository<Users, Long> {
    Users findByUsername(String username);
    List<Users> findAllByRole(Role role);
}
