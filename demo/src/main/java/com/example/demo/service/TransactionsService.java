package com.example.demo.service;


import com.example.demo.entitys.SubPartner;
import com.example.demo.entitys.VerifyTransactionsEntity;
import com.example.demo.repositorys.VerifyTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class TransactionsService {

    @Autowired
    private ImportService importService;

    @Autowired
    private VerifyTransactionRepository verifyTransactionRepository;


    public String importData(MultipartFile file) {

        importService.importExcelFile(file, VerifyTransactionsEntity.class, verifyTransactionRepository);

        return "Import Successfully";
    }
}
