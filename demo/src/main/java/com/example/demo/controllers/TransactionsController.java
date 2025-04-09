package com.example.demo.controllers;


import com.example.demo.service.TransactionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "transactions/")
public class TransactionsController {

    @Autowired
    private TransactionsService transactionsService;

    @PostMapping(value = "import")
    public String importData(@RequestParam("file") MultipartFile file) {
        return  transactionsService.importData(file);
    }

}
