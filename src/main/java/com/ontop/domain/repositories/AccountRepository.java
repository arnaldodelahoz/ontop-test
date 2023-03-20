package com.ontop.domain.repositories;

import com.ontop.domain.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {
    Optional<Account> findByUserId(Long userId);
    Optional<Account> findFirstByUserId(Long userId);
    Boolean existsByUserId(Long userId);


}
