package com.example.demo.repositorys;

import com.example.demo.entitys.ImportDataEntitys;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImportRepository extends JpaRepository<ImportDataEntitys, Long> {
}
