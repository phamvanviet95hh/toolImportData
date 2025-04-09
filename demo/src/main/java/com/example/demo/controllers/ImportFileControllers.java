package com.example.demo.controllers;

import com.example.demo.entitys.PartnerEntity;
import com.example.demo.service.PartnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("import-file")
public class ImportFileControllers {

    @Autowired
    private PartnerService partnerService;

    @PostMapping("/import")
    public String importFile() {

        // get list data in table partners

        Optional<PartnerEntity> partnerEntityList = partnerService.findById(3);

        return partnerEntityList.get().getName();
    }

}
