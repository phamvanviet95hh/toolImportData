package com.example.demo.entitys;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Table(name = "partners")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PartnerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String address;
    private String apiKey;
    private String code;
    private String consumerId;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String email;
    private Boolean isEmailVerified;
    private String name;
    private String partnerRank;
    private String partnerType;
    private String partnerNumber;
    private String representative;
    private String status;
    private String taxNumber;
    private String username;
    private Integer wardId;
    private String phoneNumber;

}
