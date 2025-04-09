package com.example.demo.service;


import com.example.demo.entitys.VerifyQuota;
import com.example.demo.repositorys.VerifyQuotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class VerifyQuotasService {

    @Autowired
    private ImportService importService;

    @Autowired
    private VerifyQuotaRepository verifyQuotaRepository;


    public String importData(MultipartFile file) {

        importService.importExcelFile(file, VerifyQuota.class, verifyQuotaRepository);
        return "Import Successfully";

    }
}
