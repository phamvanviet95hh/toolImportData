package com.example.demo.repositorys;

import com.example.demo.entitys.VerifyQuota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VerifyQuotaRepository extends JpaRepository<VerifyQuota, Long> {
}
