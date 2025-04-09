package com.example.demo.service;

import com.example.demo.repositorys.ImportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImportService {

    @Autowired
    private ImportRepository importRepository;

}
