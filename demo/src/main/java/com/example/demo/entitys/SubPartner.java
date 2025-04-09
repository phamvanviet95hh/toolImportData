package com.example.demo.entitys;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@Table(name = "sub_partners")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubPartner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
    private String createdBy;
    String code;
    String name;
    String phoneNumber;
    String address;
    Integer wardId;
    String status;
    Boolean isPartner = false;
    String inactiveReason;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "partner_id", nullable = false)
    @JsonManagedReference
    private PartnerEntity partner;
}
