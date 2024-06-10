package com.javasproject.eventmanagement.repository;

import com.javasproject.eventmanagement.entity.Contract;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContractRepository extends JpaRepository<Contract, String> {
    Optional<Contract> findById(String id);
    List<Contract> findAllByDeletedFalse();

    @Modifying
    @Transactional
    @Query("update Contract c set c.sumPaid = c.sumPaid + :sumPaidValue where c.id = :id")
    int updateIncreaseContractSumPaid(@Param("id") String id, @Param("sumPaidValue") double sumPaidValue);

    @Modifying
    @Transactional
    @Query("update Contract c set c.sumPaid = c.sumPaid - :sumPaidValue where c.id = :id")
    int updateDecreaseContractSumPaid(@Param("id") String id, @Param("sumPaidValue") double sumPaidValue);

    @Modifying
    @Transactional
    @Query("update Contract c set c.status = :status where c.id = :id")
    int updateContractStatus(@Param("id") String id, @Param("status") String status);

    @Modifying
    @Transactional
    @Query("update Contract c set c.status = 'Fully Paid' where c.sumPaid >= c.totalValue")
    int updateContractStatusFullyPaid();

    @Modifying
    @Transactional
    @Query("update Contract c set c.status = 'Part Paid' where c.sumPaid < c.totalValue")
    int updateContractStatusPartPaid();

    @Modifying
    @Transactional
    @Query("update Contract c set c.status = 'Deposited' where c.sumPaid < c.totalValue")
    int updateContractStatusDeposited();
}
