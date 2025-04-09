package com.example.demo.service;

import com.example.demo.entitys.SubPartner;
import com.example.demo.repositorys.SubPartnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class SubPartnerService {

    @Autowired
    private SubPartnerRepository subPartnerRepository;

    @Autowired
    private ImportService importService;


    public String importData(MultipartFile file) {

        importService.importExcelFile(file, SubPartner.class, subPartnerRepository);

        return "Import Successfully";
    }
}
