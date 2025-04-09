package com.example.demo.repositorys;

import com.example.demo.entitys.VerifyTransactionsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VerifyTransactionRepository extends JpaRepository<VerifyTransactionsEntity, Long> {
}
