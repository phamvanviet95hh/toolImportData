package com.example.demo.entitys;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "data")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ImportDataEntitys {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String ma_cskcb;
    private String name_cskcb;
    private String tuyen_cmkt;
    private String rank_bv;
    private String address;


}
