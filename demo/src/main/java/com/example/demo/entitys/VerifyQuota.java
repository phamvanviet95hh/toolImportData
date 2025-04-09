package com.example.demo.entitys;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Table(name = "verify_quotas")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VerifyQuota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
    private String createdBy;
    String contractName;
    String contractCode;
    LocalDate contractAt;
    Long quota;     // định mức
    Long used;      // đã dùng
    Long remain;    // còn lại
    String rank;
    LocalDateTime activeAt;     // ngày kích hoạt
    LocalDateTime expiredAt;    // hạn sử dụng
    String status;
    String cancelledReason;
    String cancelledBy;
    String providerConfigCode;
    String type;// user hoặc transaction

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "verifyQuota", fetch = FetchType.LAZY)
    @JsonBackReference(value = "verify_transactions")
    List<VerifyTransactionsEntity> transactions;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "partner_id", nullable = false)
    @JsonManagedReference
    private PartnerEntity partner;
}
