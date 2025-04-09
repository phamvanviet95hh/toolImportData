package com.example.demo.service;

import com.example.demo.entitys.PartnerEntity;
import com.example.demo.repositorys.PartnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service
public class PartnerService {

    @Autowired
    private PartnerRepository partnerRepository;

    @Autowired
    private ImportService importService;

    @Value("${spring.data.import}")
    private String location;

    public Optional<PartnerEntity> findById(int i) {

        return partnerRepository.findById((long) i);

    }

    public String importData(MultipartFile file) {
        importService.importExcelFile(file, PartnerEntity.class, partnerRepository);
        return "Import Successfully";

    }
}
