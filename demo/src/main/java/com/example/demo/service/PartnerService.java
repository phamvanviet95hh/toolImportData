package com.example.demo.service;

import com.example.demo.entitys.PartnerEntity;
import com.example.demo.repositorys.PartnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PartnerService {

    @Autowired
    private PartnerRepository partnerRepository;


    public Optional<PartnerEntity> findById(int i) {

        return partnerRepository.findById((long) i);

    }
}
