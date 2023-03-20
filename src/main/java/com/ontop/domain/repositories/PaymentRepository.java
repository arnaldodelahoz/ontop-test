package com.ontop.domain.repositories;

import com.ontop.domain.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, UUID> {

    @Modifying
    @Query("UPDATE Payment p SET p.status = :newStatus, p.updatedAt = CURRENT_TIMESTAMP WHERE p.code = :code")
    int updateStatusByCode(@Param("code") UUID code, @Param("newStatus") String newStatus);

}
