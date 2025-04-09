package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.PartnerService;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "partner/")
public class PartnerController {

    @Autowired
    private PartnerService partnerService;

    @PostMapping(value = "import")
    public String importData(@RequestParam("file") MultipartFile file) {
        return  partnerService.importData(file);
    }

}
