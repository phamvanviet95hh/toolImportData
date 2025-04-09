package com.example.demo.controllers;


import com.example.demo.service.SubPartnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "sub-partner/")
public class SubPartnerController {

    @Autowired
    private SubPartnerService subPartnerService;

    @PostMapping(value = "import")
    public String importData(@RequestParam("file") MultipartFile file) {
        return  subPartnerService.importData(file);
    }

}
