package com.example.demo.repositorys;

import com.example.demo.entitys.SubPartner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubPartnerRepository extends JpaRepository<SubPartner, Long> {
}
